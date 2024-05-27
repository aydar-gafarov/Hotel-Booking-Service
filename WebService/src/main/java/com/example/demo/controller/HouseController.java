package com.example.demo.controller;


import com.example.demo.entity.House;
import com.example.demo.entity.User;
import com.example.demo.service.HouseService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

    @GetMapping("/mainPage")
    public String mainPage(Map<String, Object> model) {
        model.put("houses", houseService.getAll());
        return "mainPage";
    }

    @PostMapping("/mainPage")
    public String add(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String name,
            @RequestParam List<MultipartFile> photos,
            @RequestParam String address,
            @RequestParam String district,
            @RequestParam String city,
            @RequestParam String price, Map<String, Object> model) throws IOException {
        User user = userService.findByEmail(userDetails.getUsername());
        House house = new House(name, price, user, district, city, address);
        houseService.save(house, user, photos);
        userService.addHouse(user, house);
        List<House> houses = houseService.getAll();
        model.put("houses", houses);
        return "mainPage";
    }


}
