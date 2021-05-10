package com.odev.yemektarifiodevi.model.dtos;

import lombok.Data;

@Data
public class AnswerDTO {
    private String answer;
    private Long id;
    private Long userID;
    private String userPhotoUrl;
    private String username;
    private Long commentID;
}
