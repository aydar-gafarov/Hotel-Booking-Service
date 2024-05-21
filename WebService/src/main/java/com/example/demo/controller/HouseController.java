package com.example.demo.controller;


import com.example.demo.dto.HouseDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.entity.House;
import com.example.demo.entity.User;
import com.example.demo.service.HouseService;
import com.example.demo.service.HouseServiceImpl;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

//    @ModelAttribute("house")
//    public HouseDto houseDto() {
//        return new HouseDto();
//    }
    @GetMapping("/mainPage")
    public String mainPage(Map<String, Object> model) {
        model.put("houses", houseService.getAll());
        return "mainPage";
    }

//    @PostMapping("/mainPage")
//    public String mainPagePost(@ModelAttribute("house") HouseDto houseDto, Model model, @AuthenticationPrincipal User user){
//        try {
//            houseService.save(houseDto, user);
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//        return "mainPage";
//    }

@PostMapping("/mainPage")
public String add(
        @AuthenticationPrincipal UserDetails userDetails,
        @RequestParam String name,
        @RequestParam String price, Map<String, Object> model) throws IOException {
        User user = userService.findByEmail(userDetails.getUsername());
        HouseDto houseDto = new HouseDto(name, price, null,user);
    houseService.save(houseDto, user);
    List<House> houses = houseService.getAll();
    model.put("houses", houses);
    return "mainPage";
}
}
