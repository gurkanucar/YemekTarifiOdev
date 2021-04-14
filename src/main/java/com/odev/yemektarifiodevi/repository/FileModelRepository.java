package com.odev.yemektarifiodevi.repository;

import com.odev.yemektarifiodevi.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileModelRepository extends JpaRepository<FileModel,Long> {


}
