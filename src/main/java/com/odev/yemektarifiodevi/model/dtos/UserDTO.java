package com.odev.yemektarifiodevi.model.dtos;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String surname;
    private String email;
    private String mobile;
    private String password;

    private boolean resetPassword;

}
