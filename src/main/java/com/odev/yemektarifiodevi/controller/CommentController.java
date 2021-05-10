package com.odev.yemektarifiodevi.controller;

import com.odev.yemektarifiodevi.model.comment.Comment;
import com.odev.yemektarifiodevi.model.dtos.CommentDTO;
import com.odev.yemektarifiodevi.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/foodID/{id}")
    public ResponseEntity getList(@PathVariable Long id) {
        return commentService.getAllCommentsByFoodId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return commentService.getById(id);
    }

    @PostMapping("/foodID/{id}")
    public ResponseEntity create(@PathVariable Long id, @RequestBody CommentDTO comment) {
        return commentService.createComment(id, comment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        return commentService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Comment comment){return commentService.update(comment);}

}