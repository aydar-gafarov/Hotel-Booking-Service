package com.example.demo.dto;

import com.example.demo.entity.User;

public class HouseDto {

    private String name;

    private String price;

    private String filename;

    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public HouseDto() {
    }

    public String getOwnerName() {
        if (owner == null) {
            return "!null!";
        }
        return owner.getEmail();
    }
    public HouseDto(String name, String price, String filename, User owner) {
        this.name = name;
        this.price = price;
        this.filename = filename;
        this.owner = owner;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
