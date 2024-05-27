package com.example.demo.service;


import com.example.demo.entity.House;
import com.example.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HouseService {

    House save(House house, User user, List<MultipartFile> photos);

    List<House> getByOwner(Long id);
    String savePhotoOld(MultipartFile file);

    List<House> getAll();

}
