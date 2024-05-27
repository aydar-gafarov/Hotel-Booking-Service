package com.example.demo.service;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.entity.House;
import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findById(Long id);
    User findByEmail(String email);
    User save(UserRegistrationDto user);
    List<User> getAll();
    void addHouse(User user, House house);

    UserDetails loadUserByUsername(String username);
}