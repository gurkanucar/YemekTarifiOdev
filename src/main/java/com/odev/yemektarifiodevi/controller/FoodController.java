package com.odev.yemektarifiodevi.controller;

import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.service.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodService foodService;


    @GetMapping
   private ResponseEntity getAll(){
       return foodService.getAllFoods();
   }

   @PostMapping
    private ResponseEntity create(@RequestBody Food food){
        return foodService.createFood(food);
   }

   @GetMapping("/{id}")
    private ResponseEntity getById(@PathVariable Long id){
        return foodService.getById(id);
   }

    @GetMapping("/userID/{id}")
    private ResponseEntity getByUserId(@PathVariable Long id){
        return foodService.getFoodsByUserId(id);
    }


   @DeleteMapping("/{id}")
   private ResponseEntity deleteById(@PathVariable Long id){
       return foodService.deleteById(id);
   }


}
