package com.example.demo.service;


import com.example.demo.dto.HouseDto;
import com.example.demo.entity.House;
import com.example.demo.entity.User;

import java.util.List;

public interface HouseService {

    House save(HouseDto houseDto, User user);

    List<House> getByOwner(Long id);

    List<House> getAll();

}
