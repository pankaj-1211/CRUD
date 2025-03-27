package com.crud.demo.controller;

import com.crud.demo.model.Category;
import com.crud.demo.model.Product;
import com.crud.demo.service.CategoryService;
import com.crud.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Page<Category> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.getAllCategories(pageable);
    }


    @PostMapping
    public void createCategoryWithProducts(@RequestBody Category category) {

        for (Product product : category.getProducts()) {
            product.setCategories(category);
        }
        categoryService.addCategory(category);
    }

    @GetMapping("/{id}")
    public void getCategoryById(@PathVariable Long id){

        categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public void updateCategoryById(@PathVariable Long id, @RequestBody Category cat){

        categoryService.updateCategory(id,cat);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){

        categoryService.deleteCategory(id);
    }

}
