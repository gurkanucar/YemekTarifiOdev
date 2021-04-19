package com.odev.yemektarifiodevi.service.comment;

import com.odev.yemektarifiodevi.model.comment.Comment;
import com.odev.yemektarifiodevi.model.dtos.CommentDTO;
import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.repository.CommentRepository;
import com.odev.yemektarifiodevi.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    FoodRepository foodRepo;

    @Autowired
    CommentRepository commentRepo;

    @Override
    public ResponseEntity<CommentDTO> createComment(Long foodID, Comment comment) {
        Food food= foodRepo.findById(foodID).orElse(null);

        if(food!=null){
            comment.setFood(food);
            return new ResponseEntity<>(convertCommentToDTO(commentRepo.save(comment)), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<CommentDTO> getById(Long id) {
        Comment comment = commentRepo.findById(id).orElse(null);
        return new ResponseEntity<>(convertCommentToDTO(comment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getAllCommentsByFoodId(Long id) {
        List<Comment> commentList = commentRepo.findAllByFoodId(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDTOS.add(convertCommentToDTO(comment));
        }
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommentDTO> deleteById(Long id) {
        Comment comment = commentRepo.findById(id).orElse(null);
        if (comment == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        comment.setDeleted(true);
        return new ResponseEntity<>(convertCommentToDTO(commentRepo.save(comment)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(Comment comment) {
        Comment existing = commentRepo.findById(comment.getId()).orElse(null);
        if(existing == null) return  new ResponseEntity(HttpStatus.NOT_FOUND);
        existing.setComment(comment.getComment());
        existing.setImage(comment.getImage());
        return new ResponseEntity<>(convertCommentToDTO(commentRepo.save(existing)), HttpStatus.OK);
    }

    private CommentDTO convertCommentToDTO(Comment comment) {
        if (comment != null) {
            CommentDTO dto = new CommentDTO();
            dto.setComment(comment.getComment());
            dto.setFoodID(comment.getFood().getId());
            dto.setId(comment.getId());
            dto.setUserID(comment.getUser().getId());
            dto.setUserPhotoUrl(comment.getUser().getProfilePhoto() != null ? comment.getUser().getProfilePhoto().getUrl() : null);
            return dto;
        }
        return null;
    }

}
