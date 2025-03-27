package com.crud.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Category getCategories() {
        return category;
    }

    public void setCategories(Category categories) {
        this.category = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "categories=" + category +
                ", id=" + id +
                ", productName='" + productName + '\'' +
                '}';
    }

    public Product(Category categories, Long id, String productName) {
        this.category = categories;
        this.id = id;
        this.productName = productName;
    }

    public Product(){

    }
}
