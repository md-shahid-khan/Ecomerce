package com.ecomerce.controller;

import com.ecomerce.Category;
import com.ecomerce.service.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
//@RequestMapping("/api") we can also use in this way for /api is constant for all controller for this class
public class CategoryController {

    CategoriesService categoriesService;

    public CategoryController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> category() {
        List<Category> list = categoriesService.getAllCategoriesList();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {

        categoriesService.createCategory(category);
        return new ResponseEntity<>("category is created successfully", HttpStatus.OK);

    }

    @DeleteMapping("/api/admin/delete-category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try {
            String status = categoriesService.deleteCategory(categoryId);
            return new ResponseEntity<String>(status, HttpStatus.OK);
//            return ResponseEntity.ok() || .badRequest || .notFount || .status || .body("string")
//            we can also use in this manager this one way of doing this

        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }

    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateDetail(@RequestBody Category category, @PathVariable Long categoryId) {
        try {
            Category updateCategory = categoriesService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category with id: " + category.getCategoryId() + "is update", HttpStatus.OK);

        } catch (ResponseStatusException e) {

            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }

    }

}
