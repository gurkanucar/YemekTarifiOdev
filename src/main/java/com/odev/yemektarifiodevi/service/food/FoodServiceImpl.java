package com.odev.yemektarifiodevi.service.food;

import com.odev.yemektarifiodevi.model.food.Category;
import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepo;

    @Override
    public ResponseEntity<Food> createFood(Food food) {
        return new ResponseEntity<>(foodRepo.save(food), HttpStatus.OK);
    }

    /*@Override
    public ResponseEntity<Food> updateFood(Food food) throws InvocationTargetException, IllegalAccessException {
        Food existingFood = foodRepo.findById(food.getId()).orElse(null);
        if (existingFood == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
        notNull.copyProperties(existingFood, food);
        return new ResponseEntity<>(foodRepo.save(existingFood), HttpStatus.OK);
    }*/

    @Override
    public ResponseEntity<Food> getById(Long id) {
        return new ResponseEntity<>(foodRepo.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Food>> getAllFoods() {
        return new ResponseEntity<>(foodRepo.findAll(), HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Food> deleteById(Long id) {
        Food food = foodRepo.findById(id).orElse(null);
        if (food == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        food.setDeleted(true);
        return new ResponseEntity<>(foodRepo.save(food), HttpStatus.OK);
    }
}
