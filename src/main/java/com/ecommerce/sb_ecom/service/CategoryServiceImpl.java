package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.exceptions.APIException;
import com.ecommerce.sb_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories() {

        List<Category>categories=categoryRepository.findAll();
        if(categories.isEmpty())
        {
           throw new APIException("No category Created till now");
        }

        List<CategoryDTO> categoryDTOs=categories.stream().map(category -> modelMapper.map(category,CategoryDTO.class)).toList();
        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.setContent(categoryDTOs);

        return categoryResponse;
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
       Category existingCategory=categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
       if(existingCategory!=null)
       {
           throw new APIException("Category with the name:"+categoryDTO.getCategoryName()+"already exist");
       }

       // DTO -> Entity
        Category category=modelMapper.map(categoryDTO, Category.class);
        Category savedCategory=categoryRepository.save(category);


        // Entity -> DTO

        return modelMapper.map(savedCategory,CategoryDTO.class);



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
