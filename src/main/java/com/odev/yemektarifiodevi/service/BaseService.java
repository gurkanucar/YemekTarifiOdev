package com.odev.yemektarifiodevi.service;

import com.odev.yemektarifiodevi.model.user.User;
import com.odev.yemektarifiodevi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    private UserRepository repo;
    public ResponseEntity<User> getByUsername(String username) {
        User user = repo.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public String getAuthUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
