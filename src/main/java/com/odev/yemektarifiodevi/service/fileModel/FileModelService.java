package com.odev.yemektarifiodevi.service.fileModel;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileModelService {

    ResponseEntity<Object> storeFile(MultipartFile file);
    void checkFolderExists(String fileName, File folder);
    Resource loadFileAsResource(String fileName);
    ResponseEntity storeMultipleFiles(MultipartFile[] files);

}
