package com.crud.demo.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Table(name="categories")
public class Category {

    @Id
    private Long id;
    private String categoryName;

    @OneToMany(mappedBy = "category" , cascade=CascadeType.ALL)
    private List<Product> products;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", id=" + id +
                ", products=" + products +
                '}';
    }

    public Category(String categoryName, Long id, List<Product> products) {
        this.categoryName = categoryName;
        this.id = id;
        this.products = products;
    }

   public Category(){

   }
}
