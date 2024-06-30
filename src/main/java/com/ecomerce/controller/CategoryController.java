package com.ecomerce.controller;

import com.ecomerce.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
     private List<Category> categories = new ArrayList<>();

     @GetMapping("/api/public/categories")
   public List<Category> category(){
        return categories;

    }
    @PostMapping("/api/public/categories")
    public String createCategory(@RequestBody Category category){
         categories.add(category);
         return "category is create successfully";
    }

}
