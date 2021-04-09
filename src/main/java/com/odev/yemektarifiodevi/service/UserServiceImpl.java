package com.odev.yemektarifiodevi.service;

import com.odev.yemektarifiodevi.model.User;
import com.odev.yemektarifiodevi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepo;

    @Override
    public User getByID(Long ID) {
        return userRepo.findById(ID).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
