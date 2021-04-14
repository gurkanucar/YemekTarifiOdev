package com.odev.yemektarifiodevi.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odev.yemektarifiodevi.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User extends BaseEntity {

    private String name;
    private String surname;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    //@Setter(AccessLevel.NONE)
    private String password;

    private boolean resetPassword;

    @JsonIgnore
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    private Role role;

    /*@OneToMany
    private List<Food> myRecipes;

    @OneToMany
    private List<Food>  savedRecipes;

    @OneToOne
    private FileModel profilePhoto;
*/
    /*public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
        */
}
