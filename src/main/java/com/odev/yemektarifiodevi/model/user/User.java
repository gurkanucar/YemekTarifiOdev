package com.odev.yemektarifiodevi.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odev.yemektarifiodevi.model.BaseEntity;
import com.odev.yemektarifiodevi.model.FileModel;
import com.odev.yemektarifiodevi.model.food.Food;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class User extends BaseEntity implements Serializable {

    private String name;
    private String surname;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;

    private int complaintCount;

    @JsonIgnore
    //@Setter(AccessLevel.NONE)
    private String password;

    private boolean resetPassword;

    @JsonIgnore
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    private Role role;

/*
    @JsonIgnore
    @OneToMany
    private List<Food> savedRecipes;
*/

/*
    @ManyToMany
    List<Food> savedRecipes;*/

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne
    private FileModel profilePhoto;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

}
