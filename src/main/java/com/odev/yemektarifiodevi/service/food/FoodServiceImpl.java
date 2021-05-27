package com.odev.yemektarifiodevi.service.food;

import com.odev.yemektarifiodevi.model.Search;
import com.odev.yemektarifiodevi.model.food.Category;
import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.model.user.Role;
import com.odev.yemektarifiodevi.model.user.User;
import com.odev.yemektarifiodevi.repository.CategoryRepository;
import com.odev.yemektarifiodevi.repository.FileModelRepository;
import com.odev.yemektarifiodevi.repository.FoodRepository;
import com.odev.yemektarifiodevi.repository.UserRepository;
import com.odev.yemektarifiodevi.service.BaseService;
import com.odev.yemektarifiodevi.service.UserService;
import com.odev.yemektarifiodevi.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl extends BaseService implements FoodService {

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private FileModelRepository fileRepo;

    @Autowired
    private CategoryRepository categoryRepo;


    @Override
    public ResponseEntity<Food> createFood(Food food) {
        return new ResponseEntity<>(foodRepo.save(food), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Food> updateFood(Food food) throws InvocationTargetException, IllegalAccessException {
        Food existingFood = foodRepo.findById(food.getId()).orElse(null);
        if (existingFood == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
        notNull.copyProperties(existingFood, food);
        return new ResponseEntity<>(foodRepo.save(existingFood), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Food> getById(Long id) {
        return new ResponseEntity<>(foodRepo.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> temp = foodRepo.findAll();
        Collections.reverse(temp);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Food> deleteById(Long id) {
        Food food = foodRepo.findById(id).orElse(null);

        if (food == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        User user = userRepo.findByUsername(getAuthUserName());

        if (user.getId() == food.getUser().getId() || user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.MODERATOR)) {
            food.setDeleted(true);
        }else{
            if(user.getId()!=food.getUser().getId() && !food.getUser().getRole().equals(Role.ADMIN)) {
                isUserShouldBanned(food.getUser().getId());
            }
           // return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }


        return new ResponseEntity<>(foodRepo.save(food), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getFoodsByUserId(Long id) {
        return new ResponseEntity(foodRepo.findAllByUserId(id), HttpStatus.OK);
    }


    @Override
    public ResponseEntity getSavedRecipes(Long id) {
        List<User> userList = new ArrayList<>();
        userList.add(userRepo.findById(id).get());
        List<Food> savedRecipes = foodRepo.findAllBySavedUsers(userRepo.findById(id).get());
        Collections.reverse(savedRecipes);
        return new ResponseEntity(savedRecipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getSearchedFoods(Search search) {

        if (search.getCategories() == null) {
            search.setCategories(new ArrayList<>());
        }
        if (search.getValue() == null) {
            search.setValue("");
        }

        List<Food> foodList = foodRepo.
                findAllByCategoryListInAndDeletedFalseAndFoodNameContaining(search.getCategories(), search.getValue());

        return new ResponseEntity(foodList.stream().distinct().collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getSearchedFoods(Long id) {
        List<Category> categories = new ArrayList<>();
        Category category = categoryRepo.findById(id).orElse(null);
        if (category == null) {
            return new ResponseEntity(categories, HttpStatus.OK);
        }
        categories.add(category);
        return new ResponseEntity(foodRepo.findAllByCategoryListInAndDeletedFalse(categories), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getSearchedFoods(String name) {
        return new ResponseEntity(foodRepo.findAllByFoodNameContaining(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity savedRecipeUpdate(Long userID, Long foodID) {

        User user = userRepo.findById(userID).orElse(null);
        Food food = foodRepo.findById(foodID).orElse(null);
        if (food != null && user != null) {

            User tempUser = food.getSavedUsers().stream()
                    .filter(data -> userID == data.getId())
                    .findAny()
                    .orElse(null);
            if (tempUser == null) {
                System.out.println("Eklendi");
                addToSavedRecipes(userID, foodID);
            } else {
                System.out.println("Çıkarıldı");
                deleteFromSavedRecipes(userID, foodID);
            }
        }
        return ResponseEntity.ok().build();
    }


    public int addToSavedRecipes(Long userID, Long foodID) {
        User user = userRepo.findById(userID).orElse(null);
        Food food = foodRepo.findById(foodID).orElse(null);
        if (user != null && food != null) {
            food.getSavedUsers().add(user);
            foodRepo.save(food);
            return 1;
        }
        return 0;
    }

    public int deleteFromSavedRecipes(Long userID, Long foodID) {
        User user = userRepo.findById(userID).orElse(null);
        Food food = foodRepo.findById(foodID).orElse(null);
        if (user != null && food != null) {
            food.getSavedUsers().remove(user);
            foodRepo.save(food);
            return 1;
        }
        return 0;
    }


}
