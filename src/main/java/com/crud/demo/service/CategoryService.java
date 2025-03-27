package com.crud.demo.service;
import com.crud.demo.model.Category;
import com.crud.demo.model.Product;
import com.crud.demo.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;



    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> getCategoryById(Long id){

        return categoryRepository.findById(id);
    }

    public Category addCategory(Category cat){

        categoryRepository.save(cat);
        return cat;
    }

    public void updateCategory(Long id, Category cat) {
        Optional<Category> existingCategoryOpt = categoryRepository.findById(id);

        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();

            existingCategory.setCategoryName(cat.getCategoryName());

            if (cat.getProducts() != null) {
                List<Product> updatedProducts = new ArrayList<>();

                for (Product product : cat.getProducts()) {
                    product.setCategories(existingCategory);

                    boolean productExists = existingCategory.getProducts().stream()
                            .anyMatch(existingProduct -> existingProduct.getProductName().equals(product.getProductName()));

                    if (!productExists) {
                        updatedProducts.add(product);
                    }
                }

                existingCategory.getProducts().clear();
                existingCategory.getProducts().addAll(updatedProducts);
            }


            categoryRepository.save(existingCategory);
        } else {
            throw new EntityNotFoundException("Category not found with id " + id);
        }
    }



    public void deleteCategory(Long id){

        categoryRepository.deleteById(id);

    }

}
