package com.odev.yemektarifiodevi.controller;

import com.odev.yemektarifiodevi.model.answer.Answer;
import com.odev.yemektarifiodevi.service.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/commentID/{id}")
    public ResponseEntity<List<Answer>> getList(@PathVariable Long id){
        return answerService.getAllCommentsByCommentId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getById(@PathVariable Long id){
        return answerService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Answer> deleteById(@PathVariable Long id){
        return answerService.deleteById(id);
    }


    @PostMapping("/commentID/{id}")
    public ResponseEntity<Answer> createById(@PathVariable Long id, @RequestBody Answer answer ){
        return answerService.createAnswer(id, answer);
    }


    @PutMapping
    public ResponseEntity<Answer> deleteById(@RequestBody Answer answer){
        return answerService.update(answer);
    }


}
