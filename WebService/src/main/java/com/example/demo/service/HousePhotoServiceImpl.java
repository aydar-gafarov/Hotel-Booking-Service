package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class HousePhotoServiceImpl implements HousePhotoService{
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String savePhoto(MultipartFile file) {
        try {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File newDir = new File(uploadPath);
                if (!newDir.exists()) {
                    newDir.mkdir();
                }
                String fileName = file.getOriginalFilename();
                file.transferTo(new File(newDir.getAbsolutePath() + "/" + fileName));
                return "http://localhost:8080/img/" + fileName;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
