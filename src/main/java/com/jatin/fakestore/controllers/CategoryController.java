package com.jatin.fakestore.controllers;

import com.jatin.fakestore.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping
    public List<String> getCategories(){
        return categoryService.getAllCategories();
    }
}
