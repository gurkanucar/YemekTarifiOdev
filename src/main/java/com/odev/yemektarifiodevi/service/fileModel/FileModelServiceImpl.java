package com.odev.yemektarifiodevi.service.fileModel;

import com.odev.yemektarifiodevi.config.FileStorageProperties;
import com.odev.yemektarifiodevi.model.FileModel;
import com.odev.yemektarifiodevi.repository.FileModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileModelServiceImpl implements FileModelService{

    private final Path fileStorageLocation;

    @Autowired
    FileModelRepository fileRepo;

    @Autowired
    public FileModelServiceImpl(FileStorageProperties fileStorageProperties) {
        String fileUploadPath = fileStorageProperties.getUploadDir();
        this.fileStorageLocation = Paths.get(fileUploadPath)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResponseEntity<Object> storeFile(MultipartFile file) {
        // Normalize file name
        String randomName = UUID.randomUUID().toString();
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String newName = randomName + extension;
        String fileName = StringUtils.cleanPath(newName);

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            // !FilenameUtils.getExtension(file.getOriginalFilename()).equalsIgnoreCase("wav")
           /* if (!extension.matches(".png|.jpeg|.jpg")) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            */
            // Copy file to the target location (Replacing existing file with the same name)
            File folder = new File(String.valueOf(this.fileStorageLocation));
            // Check folder exists for company
            checkFolderExists(fileName, folder);
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileDownloadUri = "/api/files/downloadFile/" + fileName;

            //you can check file extensions
            // if (extension.matches(".png|.jpeg|.jpg")) {
            FileModel tmpFile = new FileModel();
            tmpFile.setName(fileName);
            tmpFile.setUrl(fileDownloadUri);
            return new ResponseEntity<>(fileRepo.save(tmpFile), HttpStatus.OK);
            //}
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void checkFolderExists(String fileName, File folder) {
        if (!folder.exists()) {
            if (folder.mkdir()) {
                log.info("Assets folder created for fileName: {} ", fileName);
            }
        }
    }

    public Resource loadFileAsResource(String fileName) {
        log.info("loadFileAsResource request came ");
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(targetLocation.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ResponseEntity storeMultipleFiles(MultipartFile[] files) {
        List<Object> responseList = new ArrayList<>();
        List<Object> failedList = new ArrayList<>();
        for (MultipartFile file : files) {
            ResponseEntity<Object> storeFileResponse = storeFile(file);
            if (storeFileResponse.getStatusCode().is2xxSuccessful()) {
                responseList.add(storeFileResponse.getBody());
            } else {
                failedList.add(file.getOriginalFilename());
                log.error("Storing File failed with {} status", storeFileResponse.getStatusCode());
            }
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }



}
