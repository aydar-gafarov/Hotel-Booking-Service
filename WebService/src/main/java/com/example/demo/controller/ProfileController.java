package com.example.demo.controller;


import com.example.demo.entity.House;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{userId}")
    public String profile(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "profile";
    }

//    @AuthenticationPrincipal
//    UserDetails userDetails,
//    @RequestParam
//    String name,
//    @RequestParam
//    List<MultipartFile> photos,
//    @RequestParam String price, Map<String, Object> model) throws IOException {
//        User user = userService.findByEmail(userDetails.getUsername());
//        House house = new House(name, price, user);
//        houseService.save(house, user, photos);
//        List<House> houses = houseService.getAll();
//        model.put("houses", houses);
//        return "mainPage";
}
