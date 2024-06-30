package com.ecomerce.service;

import com.ecomerce.Category;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    final private List<Category> categoriesList = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategoriesList() {
        return categoriesList;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoriesList.add(category);


    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoriesList.stream().
                filter(n -> n.getCategoryId().equals(categoryId)).
                findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Fount"));

        categoriesList.remove(category);

        return "category is deleted successfully " + categoryId;

    }
}
