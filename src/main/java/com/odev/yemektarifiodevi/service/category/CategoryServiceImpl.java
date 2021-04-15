package com.odev.yemektarifiodevi.service.category;


import com.odev.yemektarifiodevi.model.food.Category;
import com.odev.yemektarifiodevi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        return new ResponseEntity<>(categoryRepo.save(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> getById(Long id) {
        return new ResponseEntity<>(categoryRepo.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryRepo.findAll(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Category> deleteById(Long id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category != null) category.setDeleted(true);
        return new ResponseEntity<>(categoryRepo.save(category), HttpStatus.OK);
    }

}
