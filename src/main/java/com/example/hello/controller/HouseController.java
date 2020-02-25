package com.example.hello.controller;

import com.example.hello.model.CategoryHouse;
import com.example.hello.model.House;
import com.example.hello.service.CategoryHouseService;
import com.example.hello.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Contended;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private CategoryHouseService categoryHouseService;

    @GetMapping
    public ResponseEntity<Iterable<House>> listHouse() {
        Iterable<House> homeStays = houseService.findAll();
        return new ResponseEntity<>(homeStays, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> detailHouse(@PathVariable Long id) {
        Optional<House> homeStay = houseService.findById(id);
        if (homeStay == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(homeStay, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<House> createHouse(@RequestBody House house) {
        if (house.getCategoryHouse() != null) {
            String categoryHouse = house.getCategoryHouse().getName();
            CategoryHouse categoryHouse1 = categoryHouseService.findByName(categoryHouse);
            house.setCategoryHouse(categoryHouse1);
        } else {
            house.setCategoryHouse(categoryHouseService.findByName("Nguyên căn"));
        }
        houseService.save(house);
        return new ResponseEntity("Thêm thành công!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<House> editHouse(@PathVariable Long id, @RequestBody House house) {
        Optional<House> house1 = houseService.findById(id);
        if (house1.isPresent()) {
            if (house.getCategoryHouse() != null) {
                String categoryHouse = house.getCategoryHouse().getName();
                CategoryHouse categoryHouse1 = categoryHouseService.findByName(categoryHouse);
                house1.get().setCategoryHouse(categoryHouse1);
            }
            if (!house.getNameHouse().equals("")) {
                house1.get().setNameHouse(house.getNameHouse());
            }

            if (house.getAmountBathRoom() != null) {
                house1.get().setAmountBathRoom(house.getAmountBathRoom());
            }
            if (house.getAmountBedRoom() != null) {
                house1.get().setAmountBedRoom(house.getAmountBedRoom());
            }
            if (!house.getDescription().equals("")) {
                house1.get().setDescription(house.getDescription());
            }
            if (!house.getImageUrl().equals("")) {
                house1.get().setImageUrl(house.getImageUrl());
            }

            houseService.save(house1.get());
            return new ResponseEntity("Sửa thành công!", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable Long id) {
        Optional<House> house = houseService.findById(id);
        if (house == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            houseService.delete(id);
            return new ResponseEntity("Xoá Thành Công!", HttpStatus.OK);
        }
    }
}
