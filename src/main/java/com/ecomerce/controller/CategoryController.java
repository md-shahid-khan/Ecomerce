package com.ecomerce.controller;

import com.ecomerce.Category;
import com.ecomerce.service.CategoriesService;
import com.ecomerce.service.CategoriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    CategoriesService categoriesService;

    public CategoryController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/api/public/categories")
    public List<Category> category() {
        return categoriesService.getAllCategoriesList();

    }

    @PostMapping("/api/public/categories")
    public String createCategory(@RequestBody Category category) {

        categoriesService.createCategory(category);
        return "category is created successfully";

    }

    @DeleteMapping("/api/admin/delete-category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try{
            String status =  categoriesService.deleteCategory(categoryId);
            return new ResponseEntity<String>(status,HttpStatus.OK);

        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }



    }

}
