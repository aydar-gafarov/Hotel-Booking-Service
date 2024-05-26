package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface HousePhotoService {

    String savePhoto(MultipartFile file);
}
