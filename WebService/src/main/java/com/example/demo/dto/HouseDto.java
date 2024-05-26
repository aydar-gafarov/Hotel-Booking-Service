package com.example.demo.dto;

import com.example.demo.entity.HousePhoto;
import com.example.demo.entity.User;

import java.util.List;

public class HouseDto {

    private String name;

    private String price;


    private List<HousePhoto> photos;
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<HousePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<HousePhoto> photos) {
        this.photos = photos;
    }

    public HouseDto() {
    }
    //
//    public String getOwnerName() {
//        if (owner == null) {
//            return "!null!";
//        }
//        return owner.getFirstName() + " " + owner.getLastName();
//    }
    public HouseDto(String name, String price, List<HousePhoto> photos, User owner) {
        this.name = name;
        this.price = price;
        this.owner = owner;
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
