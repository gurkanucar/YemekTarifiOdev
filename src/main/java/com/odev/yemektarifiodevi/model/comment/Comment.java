package com.odev.yemektarifiodevi.model.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odev.yemektarifiodevi.model.BaseEntity;
import com.odev.yemektarifiodevi.model.FileModel;
import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.model.user.User;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Where(clause = "deleted = false")
public class Comment extends BaseEntity implements Serializable {
    private String comment;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    @JsonIgnore
    private Food food;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne
    FileModel image;
}
