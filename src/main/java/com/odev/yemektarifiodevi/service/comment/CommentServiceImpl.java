package com.odev.yemektarifiodevi.service.comment;

import com.odev.yemektarifiodevi.model.comment.Comment;
import com.odev.yemektarifiodevi.model.dtos.CommentDTO;
import com.odev.yemektarifiodevi.model.food.Food;
import com.odev.yemektarifiodevi.model.user.Role;
import com.odev.yemektarifiodevi.model.user.User;
import com.odev.yemektarifiodevi.repository.CommentRepository;
import com.odev.yemektarifiodevi.repository.FileModelRepository;
import com.odev.yemektarifiodevi.repository.FoodRepository;
import com.odev.yemektarifiodevi.repository.UserRepository;
import com.odev.yemektarifiodevi.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl extends BaseService implements CommentService {

    @Autowired
    FoodRepository foodRepo;

    @Autowired
    CommentRepository commentRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private FileModelRepository fileRepo;

    @Override
    public ResponseEntity<CommentDTO> createComment(Long foodID, CommentDTO comment) {
        Food food = foodRepo.findById(foodID).orElse(null);

        if (food != null) {
            Comment temp = new Comment();
            temp.setComment(comment.getComment());
            temp.setFood(food);
            temp.setUser(userRepo.findById(comment.getUserID()).get());
            return new ResponseEntity<>(convertCommentToDTO(commentRepo.save(temp)), HttpStatus.OK);
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
        User user = userRepo.findByUsername(getAuthUserName());

        if (user.getId() == comment.getUser().getId() || user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.MODERATOR)) {
            comment.setDeleted(true);
        } else {
            if(user.getId()!=comment.getUser().getId() && !comment.getUser().getRole().equals(Role.ADMIN)) {
                isUserShouldBanned(comment.getUser().getId());
            }
            //  return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(convertCommentToDTO(commentRepo.save(comment)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity update(Comment comment) {
        Comment existing = commentRepo.findById(comment.getId()).orElse(null);
        if (existing == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
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
            dto.setUsername(comment.getUser().getUsername());
            dto.setUserPhotoUrl(comment.getUser().getProfilePhoto() != null ? comment.getUser().getProfilePhoto().getUrl() : fileRepo.findById(16L).get().getUrl());
            return dto;
        }
        return null;
    }

}
