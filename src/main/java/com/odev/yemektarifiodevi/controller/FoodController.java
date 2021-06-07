package com.odev.yemektarifiodevi.controller;

import com.odev.yemektarifiodevi.model.Search;
import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.service.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodService foodService;


    @GetMapping
    public ResponseEntity getAll() {
        return foodService.getAllFoods();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Food food) {
        return foodService.createFood(food);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Food food) throws InvocationTargetException, IllegalAccessException {
        return foodService.updateFood(food);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return foodService.getById(id);
    }

    @GetMapping("/userID/{id}")
    public ResponseEntity getByUserId(@PathVariable Long id) {
        return foodService.getFoodsByUserId(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        return foodService.deleteById(id);
    }

    @GetMapping("/savedRecipes/{id}")
    public ResponseEntity getSavedRecipes(@PathVariable Long id) {
        return foodService.getSavedRecipes(id);
    }


    @PostMapping("/search")
    public ResponseEntity filter(@RequestBody Search search) {
        return foodService.getSearchedFoods(search);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity getByCategory(@PathVariable Long id) {
        return foodService.getSearchedFoods(id);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return foodService.getSearchedFoods(name);
    }

    @GetMapping("/savedRecipes/{userID}/{foodID}")
    public ResponseEntity savedRecipeUpdate(@PathVariable Long userID,@PathVariable Long foodID) {
        return foodService.savedRecipeUpdate(userID,foodID);
    }


}
