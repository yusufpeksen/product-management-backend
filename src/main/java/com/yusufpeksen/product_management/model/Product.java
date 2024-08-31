package com.yusufpeksen.product_management.model;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "create_time", nullable = false)
    private Time createTime;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Product() {}

    public Product(Category category, Time createTime, double price, String description, String name) {
        this.category = category;
        this.createTime = createTime;
        this.price = price;
        this.description = description;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Time getCreateTime() {
        return createTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
