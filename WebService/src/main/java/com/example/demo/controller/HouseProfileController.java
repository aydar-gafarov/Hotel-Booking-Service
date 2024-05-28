package com.example.demo.controller;


import com.example.demo.entity.House;
import com.example.demo.repository.HouseRepository;
import com.example.demo.service.HouseService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HouseProfileController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping("/house/{houseId}")
    public String houseProfile(@PathVariable Long houseId, Model model) {
        House house = houseRepository.findById(houseId);
        model.addAttribute("house", house);
        return "houseProfile";
    }
}
