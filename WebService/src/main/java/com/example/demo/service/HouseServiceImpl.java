package com.example.demo.service;

import com.example.demo.controller.HouseController;
import com.example.demo.dto.HouseDto;
import com.example.demo.entity.House;
import com.example.demo.entity.User;
import com.example.demo.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserServiceImpl user;


    @Override
    public House save(HouseDto houseDto, @AuthenticationPrincipal User user) {
        House house = new House(houseDto.getName(), houseDto.getPrice(), houseDto.getFilename(), user);
        return houseRepository.save(house);
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

}
