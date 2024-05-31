package com.example.demo.service;


import com.example.demo.calendar.CalendarDate;
import com.example.demo.entity.House;
import com.example.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface HouseService {

    House save(House house, User user, List<MultipartFile> photos);

    List <House> filterHouse(String city, String district,
                             String address, String ownerName,
                             Integer minPrice, Integer maxPrice);

    List<House> getByOwner(Long id);
    String savePhotoOld(MultipartFile file);

    void addNewBookingDate(House house, String date, User user);

    List<List<CalendarDate>> showCalendar(House house);


    List<House> getAll();

}
