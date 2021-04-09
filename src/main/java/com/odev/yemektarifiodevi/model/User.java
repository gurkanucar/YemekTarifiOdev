package com.odev.yemektarifiodevi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@Where(clause = "deleted=false")
public class User extends BaseEntity{
    private String name;
    private String username;
    private int age;
    private String mail;

    public User(String name, String username, String mail, int age) {
        this.name = name;
        this.username = username;
        this.age = age;
        this.mail = mail;
    }

    public User() {
    }

}
