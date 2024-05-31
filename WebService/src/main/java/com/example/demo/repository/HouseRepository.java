package com.example.demo.repository;


import com.example.demo.entity.House;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> findByOwner(User owner);

    Optional<House> findById(Long id);
}
