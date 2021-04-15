package com.odev.yemektarifiodevi.model.answer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odev.yemektarifiodevi.model.BaseEntity;
import com.odev.yemektarifiodevi.model.FileModel;
import com.odev.yemektarifiodevi.model.comment.Comment;
import com.odev.yemektarifiodevi.model.user.User;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`answer`")
@Where(clause = "deleted = false")
public class Answer extends BaseEntity {

    private String answer;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    @JsonIgnore
    private Comment comment;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne
    private FileModel image;

}
