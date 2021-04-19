package com.odev.yemektarifiodevi.service.answer;


import com.odev.yemektarifiodevi.model.answer.Answer;
import org.springframework.http.ResponseEntity;

public interface AnswerService {
    ResponseEntity createAnswer(Long commentID, Answer answer);
    ResponseEntity getById(Long id);
    ResponseEntity getAllCommentsByCommentId(Long CommentID);
    ResponseEntity deleteById(Long id);
    ResponseEntity update(Answer answer);

}
