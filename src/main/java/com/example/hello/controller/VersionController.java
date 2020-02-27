package com.example.hello.controller;

import com.example.hello.model.Product;
import com.example.hello.model.Selection;
import com.example.hello.model.Variant;
import com.example.hello.model.Version;
import com.example.hello.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/versions")
public class VersionController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VariantService variantService;
    @Autowired
    private SelectionService selectionService;
    @Autowired
    private VersionService versionService;

    @GetMapping
    public ResponseEntity<Iterable<Version>> listVersion(){
        Iterable<Version> versions = versionService.findAll();
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Version> detailVersion(@PathVariable Long id){
        Optional<Version> version = versionService.findById(id);
        if(version!=null) {
            return new ResponseEntity(version, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Version> createVersion(@RequestBody Version version) {
        if (version.getProduct() != null) {
            String productName = version.getProduct().getName();
            Product product = productService.findByName(productName);
            version.setProduct(product);
        }
        if (version.getVariant() != null) {
            String variantName = version.getVariant().getName();
            Variant variant = variantService.findByName(variantName);
            version.setVariant(variant);
        }
        if (version.getSelection() != null) {
            String selectionName = version.getSelection().getName();
            Selection selection = selectionService.findByName(selectionName);
            version.setSelection(selection);
        }

        versionService.save(version);
        return new ResponseEntity("Thêm thành công! ", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Version> editVersion(@PathVariable Long id, @RequestBody Version version) {
        Optional<Version> version1 = versionService.findById(id);
        if (version1.isPresent()) {
            if (version.getProduct() != null) {
                String productName = version.getProduct().getName();
                Product product = productService.findByName(productName);
                version1.get().setProduct(product);
            }
            if (version.getSelection() != null) {
                String selectionName = version.getSelection().getName();
                Selection selection = selectionService.findByName(selectionName);
                version1.get().setSelection(selection);
            }
            if (version.getVariant() != null) {
                String variantName = version.getVariant().getName();
                Variant variant = variantService.findByName(variantName);
                version1.get().setVariant(variant);
            }
            versionService.save(version1.get());
            return new ResponseEntity("Sửa thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVersion(@PathVariable Long id){
        Optional<Version> version = versionService.findById(id);
        if (version == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            versionService.delete(id);
            return new ResponseEntity("Xoá thành công", HttpStatus.OK);
        }
    }
}
