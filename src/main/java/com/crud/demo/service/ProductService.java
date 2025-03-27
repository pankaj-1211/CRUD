package com.crud.demo.service;

import com.crud.demo.model.Product;
import com.crud.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id){

        return productRepository.findById(id);
    }

    public void addProduct(Product prod){

        productRepository.save(prod);
    }

    public void updateProduct(Long id , Product prod){

        Optional<Product> existingProduct = productRepository.findById(id);



    }

    public void deleteProduct(Long id){

        productRepository.deleteById(id);

    }

}
