package com.odev.yemektarifiodevi.service.category;

import com.odev.yemektarifiodevi.model.food.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity createCategory(Category category);
    ResponseEntity getById(Long id);
    ResponseEntity getAllCategories();
    ResponseEntity deleteById(Long id);
}
