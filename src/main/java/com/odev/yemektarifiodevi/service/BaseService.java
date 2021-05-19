package com.odev.yemektarifiodevi.service;

import com.odev.yemektarifiodevi.model.user.Role;
import com.odev.yemektarifiodevi.model.user.User;
import com.odev.yemektarifiodevi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public String getAuthUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Value("${thresholdValue}")
    private int thresholdValue;

    public boolean isUserShouldBanned(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            user.setComplaintCount(user.getComplaintCount()+1);
            userService.update(user);
            if(thresholdValue<=user.getComplaintCount() && !user.getRole().equals((Role.ADMIN))){
               userService.userBanForComplaintCount(id);
                return true;
            }
        }
        return false;
    }


}
