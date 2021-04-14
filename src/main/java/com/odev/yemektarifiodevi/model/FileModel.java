package com.odev.yemektarifiodevi.model;
import lombok.Data;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;

@Data
@Entity
@Where(clause = "deleted = false")
public class FileModel extends BaseEntity{

    private String name;
    private String url;


}
