package com.odev.yemektarifiodevi.model.other;

import lombok.Data;

@Data
public class ResetPassword {

    private String verificationCode;
    private String newPassword;
    private String repeatPassword;

}
