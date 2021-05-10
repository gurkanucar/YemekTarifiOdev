package com.odev.yemektarifiodevi.service.answer;

import com.odev.yemektarifiodevi.model.answer.Answer;
import com.odev.yemektarifiodevi.model.comment.Comment;
import com.odev.yemektarifiodevi.model.dtos.AnswerDTO;
import com.odev.yemektarifiodevi.repository.AnswerRepository;
import com.odev.yemektarifiodevi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AnswerRepository answerRepository;


    @Override
    public ResponseEntity<AnswerDTO> createAnswer(Long commentID, Answer answer) {
        Comment comment = commentRepository.findById(commentID).orElse(null);
        if(comment!=null){
            answer.setComment(comment);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(convertAnswerToDTO(answerRepository.save(answer)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AnswerDTO> getById(Long id) {
        Answer answer=answerRepository.findById(id).orElse(null);
        return new ResponseEntity<AnswerDTO>(convertAnswerToDTO(answer),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AnswerDTO>> getAllCommentsByCommentId(Long commentID) {
        List<Answer> answerList=answerRepository.findAllByCommentId(commentID);
        List<AnswerDTO> answerDTOS=new ArrayList<>();

        for (Answer answer:answerList){
            answerDTOS.add(convertAnswerToDTO(answer));
        }
        return new ResponseEntity<>(answerDTOS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AnswerDTO> deleteById(Long id) {
        Answer answer=answerRepository.findById(id).orElse(null);
        if(answer==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        answer.setDeleted(true);
        return new ResponseEntity<>(convertAnswerToDTO(answerRepository.save(answer)),HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(Answer answer) {
        Answer existing = answerRepository.findById(answer.getId()).orElse(null);
        if(existing!=null){
            existing.setAnswer(answer.getAnswer());
        }
        return new ResponseEntity<>(convertAnswerToDTO(answerRepository.save(existing)),HttpStatus.OK);
    }

    private AnswerDTO convertAnswerToDTO(Answer answer) {
        if (answer != null) {
            AnswerDTO dto = new AnswerDTO();
            dto.setAnswer(answer.getAnswer());
            dto.setCommentID(answer.getComment().getId());
            dto.setId(answer.getId());
            dto.setUserID(answer.getUser().getId());
            dto.setUsername(answer.getUser().getUsername());
            dto.setUserPhotoUrl(answer.getUser().getProfilePhoto() != null ? answer.getUser().getProfilePhoto().getUrl() : null);
            return dto;
        }
        return null;
    }


}
