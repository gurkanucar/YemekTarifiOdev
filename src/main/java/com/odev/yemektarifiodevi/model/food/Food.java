package com.odev.yemektarifiodevi.model.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odev.yemektarifiodevi.model.BaseEntity;
import com.odev.yemektarifiodevi.model.FileModel;
import com.odev.yemektarifiodevi.model.user.User;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Where(clause = "deleted = false")
public class Food extends BaseEntity implements Serializable {

    private String foodName;
    private String recipe;
    private String ingredients;

    @OneToMany
    private List<Category> categoryList;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne
    private FileModel image;

    private Long completedCount;
    private Double rankStar;
    private int hardness;

    private int prepTime;
    private int personCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @ManyToMany
    private List<User> savedUsers;
}
