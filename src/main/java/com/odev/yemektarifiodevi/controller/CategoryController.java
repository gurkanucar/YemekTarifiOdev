package com.odev.yemektarifiodevi.controller;

import com.odev.yemektarifiodevi.model.food.Category;
import com.odev.yemektarifiodevi.repository.CategoryRepository;
import com.odev.yemektarifiodevi.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getByID(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteByID(@PathVariable Long id){
        return categoryService.deleteById(id);
    }










}
