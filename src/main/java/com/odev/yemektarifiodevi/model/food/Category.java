package com.odev.yemektarifiodevi.model.food;

import com.odev.yemektarifiodevi.model.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.io.File;

@Data
@Entity
@Where(clause = "deleted = false")
public class Category extends BaseEntity {
    private String nameTurkish;
    private String nameEnglish;
    private File categoryImage;

}
