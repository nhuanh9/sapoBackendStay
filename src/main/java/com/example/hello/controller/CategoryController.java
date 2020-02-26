package com.example.hello.controller;

import com.example.hello.model.Category;
import com.example.hello.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> listCategoryHome() {
        Iterable<Category> category = categoryService.findAll();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> detailCategoryHome(@PathVariable("id") Long id) {
        Optional<Category> homeStay = categoryService.findById(id);
        if (homeStay == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(homeStay, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategoryHome(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity("Them thanh cong!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategoryRoom(@PathVariable("id") Long id, @RequestBody Category category) {
        Optional<Category> category1 = categoryService.findById(id);
        if (category1.isPresent()) {
            category1.get().setName(category.getName());
            categoryService.save(category1.get());
            return new ResponseEntity("Sua thanh cong!", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(id);
        return new ResponseEntity("Xoa thanh cong!", HttpStatus.OK);
    }
}
