package com.odev.yemektarifiodevi.model;

import lombok.Data;

@Data
public class User {
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
