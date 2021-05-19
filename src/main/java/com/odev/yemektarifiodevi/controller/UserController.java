package com.odev.yemektarifiodevi.controller;


import com.odev.yemektarifiodevi.config.jwt.JwtUtil;
import com.odev.yemektarifiodevi.model.AuthRequest;
import com.odev.yemektarifiodevi.model.CreateUser;
import com.odev.yemektarifiodevi.model.user.User;
import com.odev.yemektarifiodevi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    // ***************************
    // ADMIN OPERATIONS
    // ***************************

    String getAuthUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        return service.create(user);
    }


    @PutMapping
    public ResponseEntity update(@RequestBody User user) {
        return service.update(user);
    }


    @GetMapping
    public ResponseEntity<List<User>> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return service.getById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<User> getPublic(@PathVariable Long id) {
        return service.getPublic(id);
    }

    @GetMapping("/profileImage/{userID}/{imageID}")
    public ResponseEntity<User> profileImageUpdate(@PathVariable Long userID,@PathVariable Long imageID) {
        return service.profileImageUpdate(userID,imageID);
    }


    // ***************************
    // USER OPERATIONS
    // ***************************


    @GetMapping("/self")
    public ResponseEntity<User> getSelf() {
        return ResponseEntity.ok().body(service.getByUsername(getAuthUserName()));
    }


    @PutMapping("/updateProfile")
    public ResponseEntity updateProfile(@RequestBody User user) {
        return service.updateProfile(getAuthUserName(), user);
    }


    // PUBLIC
    @PostMapping("/login")
    public String creteToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            return jwtUtil.generateToken(userDetails);
        } catch (AuthenticationException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUser user) {
        return service.register(user);
    }

    @GetMapping("/count")
    public ResponseEntity getUserCount() {
        return service.getUserCount();
    }

}
