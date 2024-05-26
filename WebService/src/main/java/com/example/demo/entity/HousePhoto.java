package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "house_photo")
public class HousePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String photoLink;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    public HousePhoto(String photoLink, House house) {
        this.photoLink = photoLink;
        this.house = house;
    }

    public HousePhoto() {
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
