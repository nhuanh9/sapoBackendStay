package com.example.hello.controller;

import com.example.hello.model.Product;
import com.example.hello.model.Selection;
import com.example.hello.model.Variant;
import com.example.hello.service.CategoryService;
import com.example.hello.service.ProductService;
import com.example.hello.service.SelectionService;
import com.example.hello.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/variants")
public class VariantController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VariantService variantService;
    @Autowired
    private SelectionService selectionService;

    @GetMapping
    public ResponseEntity<Iterable<Variant>> listVariant() {
        Iterable<Variant> variants = variantService.findAll();
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variant> detailVariant(@PathVariable Long id) {
        Optional<Variant> variant = variantService.findById(id);
        if (variant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(variant, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Variant> createVariant(@RequestBody Variant variant) {
        if (variant.getProduct() != null) {
            String product = variant.getProduct().getName();
            Product product1 = productService.findByName(product);
            variant.setProduct(product1);
        } else {
            //product.setCategory(categoryService.findById("Nguyên căn"));
        }
        variantService.save(variant);
        return new ResponseEntity("Thêm thành công!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Variant> editVariant(@PathVariable Long id, @RequestBody Variant variant) {
        //Iterable<Selection> selections = selectionService.findAllByVariantId(id);
        Optional<Variant> variant1 = variantService.findById(id);
        if (variant1.isPresent()) {
            if (variant.getProduct() != null) {
                String product = variant.getProduct().getName();
                Product product1 = productService.findByName(product);
                variant1.get().setProduct(product1);
            }
            if (!variant.getName().equals("")) {
                variant1.get().setName(variant.getName());
            }
            variantService.save(variant1.get());
            return new ResponseEntity("Sửa thành công!", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
        Optional<Variant> variant = variantService.findById(id);
        //selectionService.deleteAllById(id);
        if (variant == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            variantService.delete(id);
            return new ResponseEntity("Xoá Thành Công!", HttpStatus.OK);
        }
    }
}
