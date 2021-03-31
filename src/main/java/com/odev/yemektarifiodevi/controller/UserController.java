package com.odev.yemektarifiodevi.controller;

import com.odev.yemektarifiodevi.model.User;
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

    @GetMapping
    public User getUser(){
       return new User("gurkan","grknn","grkn@hotmail.com",25);
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        List<User> userList=new ArrayList<>();

        userList.add(new User("gurkan","grknn","grkn@hotmail.com",25));
        userList.add(new User("atkn","atkn17","atkn@hotmail.com",26));
        userList.add(new User("burak","tburakg","burak@hotmail.com",23));
        userList.add(new User("mehmet","kayam17","mehmet@hotmail.com",21));
        userList.add(new User("enes","eksgl1","enes@hotmail.com",22));
        userList.add(new User("oguz","sirogzzz","oguz@hotmail.com",23));
        return userList;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUsers(@PathVariable int id){
        List<User> userList=new ArrayList<>();
        userList.add(new User("gurkan","grknn","grkn@hotmail.com",25));
        userList.add(new User("atkn","atkn17","atkn@hotmail.com",26));
        userList.add(new User("burak","tburakg","burak@hotmail.com",23));
        userList.add(new User("mehmet","kayam17","mehmet@hotmail.com",21));
        userList.add(new User("enes","eksgl1","enes@hotmail.com",22));
        userList.add(new User("oguz","sirogzzz","oguz@hotmail.com",23));
        if(id>userList.size()){
            return new ResponseEntity<>(new User(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userList.get(id),HttpStatus.OK);
    }


}
