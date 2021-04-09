package com.odev.yemektarifiodevi.controller;

import com.odev.yemektarifiodevi.model.User;
import com.odev.yemektarifiodevi.service.UserService;
import com.odev.yemektarifiodevi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public User getUser() {
        return new User("gurkan", "grknn", "grkn@hotmail.com", 25);
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUsers(@PathVariable int id) {
        return userService.getByID(id);


    }


}
