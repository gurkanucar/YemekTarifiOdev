package com.odev.yemektarifiodevi.repository;

import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    User findByEmail(String email);


}
