package com.example.demo.calendar;

import com.example.demo.entity.User;

import java.time.LocalDate;

public class CalendarDate {

    private LocalDate date;

    private Boolean booked;

    private User user;

    public User getUser() {
        return user;
    }

    public CalendarDate(LocalDate date, Boolean booked, User user) {
        this.date = date;
        this.booked = booked;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CalendarDate() {
    }

    public Boolean getBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }

    public CalendarDate(LocalDate date, Boolean booked) {
        this.date = date;
        this.booked = booked;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
