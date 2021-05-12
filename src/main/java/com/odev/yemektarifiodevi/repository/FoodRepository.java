package com.odev.yemektarifiodevi.repository;

import com.odev.yemektarifiodevi.model.food.Category;
import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {

    List<Food> findAllByFoodNameContaining(String searchedValue);

    List<Food> findAllByCategoryListInAndDeletedFalse(List<Category> categoryList);

    List<Food> findAllByCategoryListInAndDeletedFalseAndFoodNameContaining(List<Category> categoryList,String searchedValue);

    List<Food> findAllByUserId(Long id);

    List<Food> findAllBySavedUsers(User user);


   /* List<Food> findAllByS(Long id);

    List<Food> findAllByUserMyRecipes(Long id);*/

}
