package com.odev.yemektarifiodevi.model.food;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odev.yemektarifiodevi.model.BaseEntity;
import com.odev.yemektarifiodevi.model.FileModel;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Data
@Entity
@Where(clause = "deleted = false")
public class Category extends BaseEntity {
    private String nameTurkish;
    private String nameEnglish;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne
    private FileModel categoryImage;

}
