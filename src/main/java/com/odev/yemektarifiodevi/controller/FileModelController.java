package com.odev.yemektarifiodevi.controller;

import com.odev.yemektarifiodevi.service.fileModel.FileModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/files")
public class FileModelController {

    @Autowired
    private FileModelService service;

    @PostMapping("/uploadFile")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        return service.storeFile(file);
    }

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity uploadMultipleFiles(@RequestParam("file") MultipartFile[] files) {
        return service.storeMultipleFiles(files);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = service.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            return  ResponseEntity.notFound().build();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
