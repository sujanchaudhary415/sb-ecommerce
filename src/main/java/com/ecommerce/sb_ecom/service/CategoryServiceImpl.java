package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll() ;
    }

    @Override
    public void createCategory(Category category) {

       categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {


        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found"));


        categoryRepository.delete(category);
        return "CategoryId with "+categoryId+" deleted successfully";
    }

    @Override
    public Category updateCategeory(Category category, Long categoryId) {
        Category existingCategory=categoryRepository.findById(categoryId)
                          .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
        existingCategory.setCategoryName(category.getCategoryName());
       return categoryRepository.save(existingCategory);
    }
}
