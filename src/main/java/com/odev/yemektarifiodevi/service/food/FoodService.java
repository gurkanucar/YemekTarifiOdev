package com.odev.yemektarifiodevi.service.food;

import com.odev.yemektarifiodevi.model.food.Category;
import com.odev.yemektarifiodevi.model.food.Food;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface FoodService {

    ResponseEntity createFood(Food food);
    //ResponseEntity updateFood(Food food) throws InvocationTargetException, IllegalAccessException;
    ResponseEntity getById(Long id);
    ResponseEntity getAllFoods();
    ResponseEntity deleteById(Long id);
    ResponseEntity getFoodsByUserId(Long id);
}
