package com.odev.yemektarifiodevi.model.food;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odev.yemektarifiodevi.model.BaseEntity;
import com.odev.yemektarifiodevi.model.user.User;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Where(clause = "deleted = false")
public class Food extends BaseEntity {

    private String foodName;
    private String recipe;
    private String ingredients;

    @OneToMany
    private List<Category> categoryList;

    //@OneToMany
    //private List<FileModel> imageList;

    private Long completedCount;
    private Double rankStar;
    private int hardness;

    private int prepTime;
    private int personCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
