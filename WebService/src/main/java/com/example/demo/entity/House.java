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

    private int price;

    private String district;
    private String city;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "house")
    private List<HousePhoto> photos;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<HousePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<HousePhoto> photos) {
        this.photos = photos;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public House(String name, int price, User owner, String district, String city, String address) {
        this.name = name;
        this.price = price;
        this.owner = owner;
        this.district = district;
        this.city = city;
        this.address = address;
    }

    public House(String name, int price, List<HousePhoto> photos, User owner) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
