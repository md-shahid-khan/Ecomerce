package com.ecomerce.service;

import com.ecomerce.Category;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Fount"));

        categoriesList.remove(category);

        return "category is deleted successfully " + categoryId;

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> optionalCategory = categoriesList.stream().filter(n -> n.getCategoryId().equals(categoryId)).findFirst();
        if (optionalCategory.isPresent()) {
            Category exising = optionalCategory.get();
            exising.setCategoryName(category.getCategoryName());
            return exising;


        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category is not present");
        }


    }


}
