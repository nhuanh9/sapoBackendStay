package com.example.hello.controller;

import com.example.hello.model.CategoryHouse;
import com.example.hello.service.CategoryHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/category-house")
public class CategoryHouseController {
    @Autowired
    private CategoryHouseService categoryHouseService;

    @GetMapping
    public ResponseEntity<Iterable<CategoryHouse>> listCategoryHome() {
        Iterable<CategoryHouse> categoryHomes = categoryHouseService.findAll();
        return new ResponseEntity<>(categoryHomes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryHouse> detailCategoryHome(@PathVariable("id") Long id) {
        Optional<CategoryHouse> homeStay = categoryHouseService.findById(id);
        if (homeStay == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(homeStay, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryHouse> createCategoryHome(@RequestBody CategoryHouse categoryHouse) {
        categoryHouseService.save(categoryHouse);
        return new ResponseEntity("Them thanh cong!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryHouse> editCategoryRoom(@PathVariable("id") Long id, @RequestBody CategoryHouse categoryHouse) {
        Optional<CategoryHouse> categoryHome1 = categoryHouseService.findById(id);
        if (categoryHome1.isPresent()) {
            categoryHome1.get().setName(categoryHouse.getName());

            categoryHouseService.save(categoryHome1.get());
            return new ResponseEntity("Sua thanh cong!", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<CategoryHouse> categoryHouse = categoryHouseService.findById(id);
        if (categoryHouse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryHouseService.delete(id);
        return new ResponseEntity("Xoa thanh cong!", HttpStatus.OK);
    }
}
