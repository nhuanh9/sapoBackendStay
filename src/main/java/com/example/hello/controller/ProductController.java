package com.example.hello.controller;

import com.example.hello.model.Category;
import com.example.hello.model.Product;
import com.example.hello.service.CategoryService;
import com.example.hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> listProduct() {
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> detailProduct(@PathVariable Long id) {
        Optional<Product> homeStay = productService.findById(id);
        if (homeStay == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(homeStay, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (product.getCategory() != null) {
            String category = product.getCategory().getName();
            Category category1 = categoryService.findByName(category);
            product.setCategory(category1);
        } else {
            //product.setCategory(categoryService.findById("Nguyên căn"));
        }
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        product.setCreateDay(date);
        product.setTheMostNearEditDay(date);
        productService.save(product);
        return new ResponseEntity("Thêm thành công!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (product1.isPresent()) {
            if (product.getCategory() != null) {
                String category = product.getCategory().getName();
                Category category1 = categoryService.findByName(category);
                product1.get().setCategory(category1);
            }
            if (!product.getName().equals("")) {
                product1.get().setName(product.getName());
            }

            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            product1.get().setTheMostNearEditDay(date);
            productService.save(product1.get());
            return new ResponseEntity("Sửa thành công!", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            productService.delete(id);
            return new ResponseEntity("Xoá Thành Công!", HttpStatus.OK);
        }
    }
}
