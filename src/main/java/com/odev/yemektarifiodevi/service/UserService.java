package com.odev.yemektarifiodevi.service;

import com.odev.yemektarifiodevi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    ResponseEntity getByID (int ID);


    List<User> getUsers();

}
