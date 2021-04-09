package com.odev.yemektarifiodevi.service;

import com.odev.yemektarifiodevi.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getUsers();

}
