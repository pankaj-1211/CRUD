package com.crud.demo.controller;

import com.crud.demo.model.Product;
import com.crud.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProducts(pageable);
    }

    @PostMapping
    public void addProduct(@RequestBody Product prod){

        productService.addProduct(prod);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){

        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable Long id, @RequestBody Product prod){

        productService.updateProduct(id,prod);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){

        productService.deleteProduct(id);
    }

}
