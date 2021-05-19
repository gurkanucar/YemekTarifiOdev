package com.odev.yemektarifiodevi.service;


import com.odev.yemektarifiodevi.model.BaseEntity;
import com.odev.yemektarifiodevi.model.CreateUser;
import com.odev.yemektarifiodevi.model.FileModel;
import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.model.other.Message;
import com.odev.yemektarifiodevi.model.user.Role;
import com.odev.yemektarifiodevi.model.user.User;
import com.odev.yemektarifiodevi.repository.FileModelRepository;
import com.odev.yemektarifiodevi.repository.FoodRepository;
import com.odev.yemektarifiodevi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private FileModelRepository fileRepo;

    public ResponseEntity<User> create(User entity) {
        entity.setUsername(entity.getEmail());
        User createdEntity = userRepository.save(entity);
        if (createdEntity != null) {
            return new ResponseEntity<>(createdEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<User> update(User user) {
        User existing = userRepository.findById(user.getId()).orElse(null);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (user.isResetPassword()) {
            existing.setPassword(user.getEmail());
            existing.setResetPassword(false);
        }

        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setName(user.getName());
        existing.setSurname(user.getSurname());
        existing.setRole(user.getRole());

        return new ResponseEntity<>(userRepository.save(existing), HttpStatus.OK);
    }

    public ResponseEntity<List<User>> list() {
        List<User> users = userRepository.findAll();
        List<User> entityList = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername() != null) {
                if (!user.getUsername().equals("admin")) {
                    entityList.add(user);
                }
            }
        }
        return new ResponseEntity<>(entityList, HttpStatus.OK);
    }

    public ResponseEntity<User> getById(long id) {
        User entity = userRepository.findById(id).orElse(null);
        User authUser = userRepository.findByUsername(getAuthUserName());

        if (entity != null) {
            if (authUser.getRole().equals(Role.ADMIN) || authUser.getId() == entity.getId()) {
                return new ResponseEntity<>(entity, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> getPublic(long id) {
        User entity = userRepository.findById(id).orElse(null);

        if (entity != null) {
            entity.setEmail(null);
            entity.setSurname(null);
            entity.setRole(null);
            entity.setVerificationCode(null);
            //entity.setResetPassword(false);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> profileImageUpdate(long userID, long imageID) {
        User entity = userRepository.findById(userID).orElse(null);
        FileModel image = fileRepo.findById(imageID).orElse(null);
        if (entity != null && image != null) {
            entity.setProfilePhoto(image);
            userRepository.save(entity);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> deleteById(long id) {
        User entity = userRepository.findById(id).orElse(null);
        User user = userRepository.findByUsername(getAuthUserName());
        if (user.getId() == id || user.getRole().equals(Role.USER)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        List<Food> foodList = foodRepo.findAllByUserId(id);
        for (Food food : foodList) {
            food.setDeleted(true);
            foodRepo.save(food);
        }
        return deleteById(entity, userRepository);
    }


    public void userBanForComplaintCount(long id) {
        User entity = userRepository.findById(id).orElse(null);
        List<Food> foodList = foodRepo.findAllByUserId(id);
        for (Food food : foodList) {
            food.setDeleted(true);
            foodRepo.save(food);
        }
        deleteById(entity, userRepository);
    }


    public ResponseEntity<User> updateProfile(String authUserName, User user) {
        User originalUser = userRepository.findByUsername(authUserName);
        originalUser.setName(user.getName());
        originalUser.setSurname(user.getSurname());
        originalUser.setEmail(user.getEmail());
        originalUser.setUsername(user.getEmail());


        userRepository.save(originalUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity register(CreateUser user) {
        User entity = new User();

        User existingUsername = userRepository.findByUsername(user.getUsername());
        if (existingUsername != null) {
            return new ResponseEntity(Message.USERNAME_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        User existingEmail = userRepository.findByEmail(user.getEmail());
        if (existingEmail != null) {
            return new ResponseEntity(Message.EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        // assign user role
        entity.setRole(Role.USER);
        entity.setEmail(user.getUsername());
        entity.setUsername(user.getUsername());
        entity.setName(user.getUsername());
        entity.setSurname(user.getUsername());
        // entity.setUsername(user.getUsername());
        // entity.setName(user.getName());
        //entity.setSurname(user.getSurname());
        entity.setPassword(user.getPassword());


        User savedUser = userRepository.save(entity);
        if (savedUser != null) {
            return new ResponseEntity(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity(Message.SYSTEM_ERROR, HttpStatus.CONFLICT);
        }
    }


    ResponseEntity<User> deleteById(BaseEntity entity, JpaRepository repo) {
        if (entity != null) {
            entity.setDeleted(true);
            repo.save(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Long> getUserCount() {
        return new ResponseEntity<>(userRepository.count(), HttpStatus.OK);
    }
}
