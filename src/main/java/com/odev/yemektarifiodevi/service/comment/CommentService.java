package com.odev.yemektarifiodevi.service.comment;

import com.odev.yemektarifiodevi.model.comment.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity createComment(Long foodID, Comment comment);
    ResponseEntity getById(Long id);
    ResponseEntity getAllCommentsByFoodId(Long foodID);
    ResponseEntity deleteById(Long id);
}
