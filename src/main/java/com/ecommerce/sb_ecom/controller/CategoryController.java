package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        List fetchedCategory=categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(fetchedCategory);

    }

    @PostMapping("/api/public/category")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category)
    {
        categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category added Successfully");
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
        try
        {
          String status=categoryService.deleteCategory(categoryId);
          return new ResponseEntity<>(status,HttpStatus.OK);

        }
        catch (ResponseStatusException e)
        {
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category,@PathVariable Long categoryId)
    {
        try
        {
            Category updateCategeory=categoryService.updateCategeory(category,categoryId);
            return ResponseEntity.ok(updateCategeory);
        }catch(ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);

    }
    }


}
