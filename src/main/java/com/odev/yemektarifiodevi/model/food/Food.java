package com.odev.yemektarifiodevi.model.food;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odev.yemektarifiodevi.model.BaseEntity;
import com.odev.yemektarifiodevi.model.FileModel;
import com.odev.yemektarifiodevi.model.user.User;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Size;
@Data
@Entity
@Where(clause = "deleted = false")
public class Food extends BaseEntity implements Serializable {

    @Size(max = 300)
    private String foodName;
    @Size(max = 4000)
    private String recipe;
    @Size(max = 4000)
    private String ingredients;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Category> categoryList;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne
    private FileModel image;

    private Long completedCount;
    private int rankStar;
    private int hardness;

    private int prepTime;
    private int personCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    //@JsonIgnore
    @ManyToMany
    private List<User> savedUsers;
}
