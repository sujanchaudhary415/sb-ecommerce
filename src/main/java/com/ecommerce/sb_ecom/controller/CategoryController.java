package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
public class CategoryController {


 private CategoryService categoryService;



    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories()
    {
        CategoryResponse categoryResponse =categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoryResponse);

    }

    @PostMapping("/api/public/category")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO saved=categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
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



