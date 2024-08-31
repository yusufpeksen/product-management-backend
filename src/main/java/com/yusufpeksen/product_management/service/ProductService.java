package com.yusufpeksen.product_management.service;

import com.yusufpeksen.product_management.exception.ProductNotFoundException;
import com.yusufpeksen.product_management.model.Product;
import com.yusufpeksen.product_management.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> getAllProducts(){
        logger.debug("Fetching all orders");
        return productRepository.findAll();
    }

    @Transactional
    public Product getProductById(Long id) {
        logger.debug("Fetching order by ID : " + id);
        Product product = productRepository.findById(id).orElseThrow(() -> {
            logger.error("Product can not find ID : " + id);
            return new ProductNotFoundException(id);
        });
        logger.debug("Fetched product : " + product);
        return product;
    }

    @Transactional
    public Product addProduct(Product newProduct) {
        logger.info("Creating new Product : " + newProduct);
        newProduct.setCreateTime(LocalTime.now());

        Product product = productRepository.save(newProduct);
        logger.info("Product saved with ID : " + newProduct.getId());

        return product;
    }

    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        logger.info("Updating product with ID : " + id);

        Product legacyProduct = productRepository.findById(id).orElseThrow(() -> {
            logger.error("Product with ID can not found : " + id);
            return new ProductNotFoundException(id);
        });

        legacyProduct.setName(updatedProduct.getName());
        legacyProduct.setDescription(updatedProduct.getDescription());
        legacyProduct.setPrice(updatedProduct.getPrice());
        legacyProduct.setCategory(updatedProduct.getCategory());

        Product savedProduct = productRepository.save(legacyProduct);
        logger.info("Order updated with ID : " + id);

        return  savedProduct;
    }

    @Transactional
    public void deleteProduct(Long id) {
        logger.info("Deleting product with ID : " + id);

        Product existingProduct = productRepository.findById(id).orElseThrow(() -> {
            logger.error("Product with ID can not found : " + id);
            return new ProductNotFoundException(id);
        });

        productRepository.delete(existingProduct);
        logger.info("Product deleted with ID : " + id);
    }
}
