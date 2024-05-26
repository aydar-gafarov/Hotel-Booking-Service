package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "house")
    private List<HousePhoto> photos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

//    @Transient
//    private List<String> photos = new ArrayList<>();

    public List<HousePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<HousePhoto> photos) {
        this.photos = photos;
    }

    //    public List<String> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<String> photos) {
//        this.photos = photos;
//    }

    public House(String name, String price, User owner) {
        this.name = name;
        this.price = price;
        this.owner = owner;
    }

    public House(String name, String price, List<HousePhoto> photos, User owner) {
        this.name = name;
        this.price = price;
        this.photos = photos;
        this.owner = owner;
    }

    public House() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
