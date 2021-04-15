package com.odev.yemektarifiodevi.model.dtos;

import lombok.Data;

@Data
public class CommentDTO {

    private String comment;
    private Long id;
    private Long userID;
    private String userPhotoUrl;
    private Long foodID;

}
