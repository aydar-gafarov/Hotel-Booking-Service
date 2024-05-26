package com.example.demo.repository;

import com.example.demo.entity.HousePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousePhotoRepository extends JpaRepository<HousePhoto, Long> {

    HousePhoto findByPhotoLink(String photoLink);

    HousePhoto findByHouseId(Long houseId);
}
