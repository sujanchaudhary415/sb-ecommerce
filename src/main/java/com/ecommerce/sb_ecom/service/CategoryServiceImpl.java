package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.exceptions.APIException;
import com.ecommerce.sb_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {

        List<Category>categories=categoryRepository.findAll();
        if(categories.isEmpty())
        {
           throw new APIException("No category Created till now");
        }
        return categories;
    }


    @Override
    public void createCategory(Category category) {
       Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());
       if(savedCategory!=null)
       {
           throw new APIException("Category with the name:"+category.getCategoryName());
       }
       categoryRepository.save(category);
    }


    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",String.valueOf(categoryId)));
        categoryRepository.delete(category);
        return "CategoryId with "+categoryId+" deleted successfully";
    }


    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory=categoryRepository.findById(categoryId)
                          .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        existingCategory.setCategoryName(category.getCategoryName());

       return categoryRepository.save(existingCategory);
    }
}
