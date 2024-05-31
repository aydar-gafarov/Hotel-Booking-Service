package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
