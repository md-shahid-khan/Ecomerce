package com.ecomerce.service;

import com.ecomerce.Category;

import java.util.List;


public interface CategoriesService {
     List<Category> getAllCategoriesList();
     void createCategory(Category category);

     String deleteCategory(Long categoryId);
}
