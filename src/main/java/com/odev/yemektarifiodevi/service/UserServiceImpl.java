package com.odev.yemektarifiodevi.service;

import com.odev.yemektarifiodevi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers() {

        List<User> userList=new ArrayList<>();

        userList.add(new User("gurkan","grknn","grkn@hotmail.com",25));
        userList.add(new User("atkn","atkn17","atkn@hotmail.com",26));
        userList.add(new User("burak","tburakg","burak@hotmail.com",23));
        userList.add(new User("mehmet","kayam17","mehmet@hotmail.com",21));
        userList.add(new User("enes","eksgl1","enes@hotmail.com",22));
        userList.add(new User("oguz","sirogzzz","oguz@hotmail.com",23));
        return userList;
    }
}
