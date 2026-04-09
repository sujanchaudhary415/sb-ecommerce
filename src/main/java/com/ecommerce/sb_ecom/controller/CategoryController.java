package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
public class CategoryController {


 private CategoryService categoryService;



    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        List<Category> fetchedCategory=categoryService.getAllCategories();
        return ResponseEntity.ok().body(fetchedCategory);

    }

    @PostMapping("/api/public/category")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category)
    {
        categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category added Successfully");
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
        String status=categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status,HttpStatus.OK);



    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category,@PathVariable Long categoryId)
    {

            Category updateCategory=categoryService.updateCategory(category,categoryId);
            return ResponseEntity.ok().body(updateCategory);
    }
    }



