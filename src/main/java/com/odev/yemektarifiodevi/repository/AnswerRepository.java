package com.odev.yemektarifiodevi.repository;

import com.odev.yemektarifiodevi.model.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {


    List<Answer> findAllByCommentId(Long id);

}
