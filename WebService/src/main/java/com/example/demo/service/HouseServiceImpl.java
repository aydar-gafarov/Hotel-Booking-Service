package com.example.demo.service;

import com.example.demo.calendar.CalendarDate;
import com.example.demo.entity.Booking;
import com.example.demo.entity.House;
import com.example.demo.entity.HousePhoto;
import com.example.demo.entity.User;
import com.example.demo.repository.HouseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private UserServiceImpl user;
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private UserRepository userRepository;
    @Override
    public House save(House house, @AuthenticationPrincipal User user, List<MultipartFile> photos) {
        List<HousePhoto> housePhotos = new ArrayList<>();
        for (MultipartFile photo : photos) {
            String photoLink = savePhotoOld(photo);
            HousePhoto housePhoto = new HousePhoto();
            housePhoto.setPhotoLink(photoLink);
            housePhoto.setHouse(house);
            housePhotos.add(housePhoto);
        }
        house.setPhotos(housePhotos);
        house.setOwner(user);
        return houseRepository.save(house);
    }

    @Override
    public List<House> filterHouse(String city, String district, String address, String ownerName, Integer minPrice, Integer maxPrice) {
        List<House> filteredHouse = new ArrayList<>();
        for (House house : houseRepository.findAll()) {
            if ((minPrice != null && minPrice > house.getPrice()) || (maxPrice != null && house.getPrice() > maxPrice)) {
                continue;
            }
            if (!isEmpty(city) && !house.getCity().equals(city)) {
                continue;
            }
            if (!isEmpty(district) && !house.getDistrict().equals(district)) {
                continue;
            }
            if (!isEmpty(address) && !house.getAddress().equals(address)) {
                continue;
            }
            if (ownerName != null && !ownerName.isEmpty()) {
                String[] nameParts = ownerName.split("\\s+");
                String firstName = nameParts[0];
                String lastName = "";
                if (nameParts.length > 1) {
                    lastName = nameParts[1];
                }

                if (!isEmpty(ownerName) && !house.getOwner().getFirstName().equals(firstName) || !house.getOwner().getLastName().equals(lastName)) {
                    continue;
                }
            }

            filteredHouse.add(house);
        }
        return filteredHouse;
    }
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Override
    public List<House> getByOwner(Long id) {
        User owner = user.findById(id);
        return houseRepository.findByOwner(owner);
    }

    @Override
    public List<House> getAll() {
        return houseRepository.findAll();
    }


    @Override
    public String savePhotoOld(MultipartFile file) {
        try {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File newDir = new File(uploadPath);
                if (!newDir.exists()) {
                    newDir.mkdir();
                }
                String fileName = file.getOriginalFilename();
                file.transferTo(new File(newDir.getAbsolutePath() + "/" + fileName));
                return "http://localhost:8080/img/" + fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void addNewBookingDate(House house, String date, User user) {
        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date);
            for (Booking booking : house.getBookings()) {
                if (booking.getDate().equals(localDate)) {
                    throw new RuntimeException("Date already booked");
                }
            }
            Booking booking = new Booking(localDate, user, house);
            house.getBookings().add(booking);
            houseRepository.save(house);
        }
    }

    @Override
    public List<List<CalendarDate>> showCalendar(House house) {
        List<List<CalendarDate>> calendar = new ArrayList<>();

        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1).withDayOfMonth(1);

        LocalDate currentDay = startOfMonth;
        List<CalendarDate> week = new ArrayList<>();

        while (!currentDay.isAfter(endOfMonth)) {
            boolean isBooked = isDateBooked(house, currentDay);
            User bookedBy = getBookedBy(house, currentDay);
            week.add(new CalendarDate(currentDay, isBooked, bookedBy));

            if (currentDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                calendar.add(new ArrayList<>(week));
                week.clear();
            }

            currentDay = currentDay.plusDays(1);
        }

        if (!week.isEmpty()) {
            calendar.add(week);
        }

        return calendar;
    }

    private boolean isDateBooked(House house, LocalDate date) {
        return house.getBookings().stream()
                .anyMatch(booking -> booking.getDate().equals(date));
    }

    private User getBookedBy(House house, LocalDate date) {
        return house.getBookings().stream()
                .filter(booking -> booking.getDate().equals(date))
                .map(Booking::getUser)
                .findFirst()
                .orElse(null);
    }




}
