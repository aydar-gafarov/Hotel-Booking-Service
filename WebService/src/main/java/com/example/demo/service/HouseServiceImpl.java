package com.example.demo.service;

import com.example.demo.entity.House;
import com.example.demo.entity.HousePhoto;
import com.example.demo.entity.User;
import com.example.demo.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserServiceImpl user;


    @Value("${upload.path}")
    private String uploadPath;


    @Override
    public House save(House house, @AuthenticationPrincipal User user, List<MultipartFile> photos) {
        List<HousePhoto> housePhotos = new ArrayList<>();
        for (MultipartFile photo : photos) {
            String photoLink = savePhotoOld(photo);
            HousePhoto housePhoto = new HousePhoto();
            housePhoto.setPhotoLink(photoLink);
            housePhoto.setHouse(house);
            housePhotos.add(housePhoto);
        }
        house.setPhotos(housePhotos);
        house.setOwner(user);
        return houseRepository.save(house);
    }

    @Override
    public List<House> filterHouse(String city, String district, String address, String ownerName, Integer minPrice, Integer maxPrice) {
        List<House> filteredHouse = new ArrayList<>();
        for (House house : houseRepository.findAll()) {
            if ((minPrice != null && minPrice > house.getPrice()) || (maxPrice != null && house.getPrice() > maxPrice)) {
                continue;
            }
            if (!isEmpty(city) && !house.getCity().equals(city)) {
                continue;
            }
            if (!isEmpty(district) && !house.getDistrict().equals(district)) {
                continue;
            }
            if (!isEmpty(address) && !house.getAddress().equals(address)) {
                continue;
            }
            if (ownerName != null && !ownerName.isEmpty()) {
                String[] nameParts = ownerName.split("\\s+"); // Разделяем строку по пробелу или пробелам
                String firstName = nameParts[0]; // Получаем первую подстроку (имя)
                String lastName = ""; // Пустая фамилия по умолчанию
                if (nameParts.length > 1) {
                    lastName = nameParts[1]; // Если есть вторая подстрока, то это фамилия
                }

                // Проверяем соответствие имени и фамилии владельца дома
                if (!isEmpty(ownerName) && !house.getOwner().getFirstName().equals(firstName) || !house.getOwner().getLastName().equals(lastName)) {
                    continue; // Если имена или фамилии не совпадают, пропускаем дом
                }
            }

            filteredHouse.add(house);
        }
        return filteredHouse;
    }
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Override
    public List<House> getByOwner(Long id) {
        User owner = user.findById(id);
        return houseRepository.findByOwner(owner);
    }

    @Override
    public List<House> getAll() {
        return houseRepository.findAll();
    }


    @Override
    public String savePhotoOld(MultipartFile file) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
