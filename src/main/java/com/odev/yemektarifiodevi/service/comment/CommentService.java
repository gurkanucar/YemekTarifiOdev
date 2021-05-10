package com.odev.yemektarifiodevi.service.comment;

import com.odev.yemektarifiodevi.model.comment.Comment;
import com.odev.yemektarifiodevi.model.dtos.CommentDTO;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity createComment(Long foodID, CommentDTO comment);
    ResponseEntity getById(Long id);
    ResponseEntity getAllCommentsByFoodId(Long foodID);
    ResponseEntity deleteById(Long id);
    ResponseEntity update(Comment comment);
}
