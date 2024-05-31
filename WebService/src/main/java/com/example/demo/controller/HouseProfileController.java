package com.example.demo.controller;


import com.example.demo.calendar.CalendarDate;
import com.example.demo.entity.House;
import com.example.demo.entity.User;
import com.example.demo.repository.HouseRepository;
import com.example.demo.service.HouseService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HouseProfileController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/house/{houseId}")
    public String houseProfile(@PathVariable Long houseId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));
        List<List<CalendarDate>> weeks = houseService.showCalendar(house);
        model.addAttribute("weeks", weeks);
        model.addAttribute("house", house);
        model.addAttribute("currentUserId", userService.findByEmail(userDetails.getUsername()).getId());
        return "house";
    }

    @PostMapping("/house/{houseId}/booking")
    public RedirectView addBookingDate(@PathVariable Long houseId, @RequestParam(required = false) String dateStr, @AuthenticationPrincipal UserDetails userDetails) {
        House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));
        User user = userService.findByEmail(userDetails.getUsername());
//        if (dateStr != null && !dateStr.isEmpty())
            houseService.addNewBookingDate(house, dateStr, user);
//        return "redirect:/house/" + houseId;
        return new RedirectView("/house/" + houseId);
    }

}
