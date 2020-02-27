package com.example.hello.controller;

import com.example.hello.model.*;
import com.example.hello.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/version-products")
public class VersionProductController {
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
    @Autowired
    private VersionProductService versionProductService;

    @GetMapping
    public ResponseEntity<Iterable<VersionProduct>> listVersionProduct() {
        Iterable<VersionProduct> versions = versionProductService.findAll();
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VersionProduct> detailVersionProduct(@PathVariable Long id) {
        Optional<VersionProduct> version = versionProductService.findById(id);
        if (version != null) {
            return new ResponseEntity(version, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//
//    @PostMapping
//    public ResponseEntity<VersionProduct> createVersion(@RequestBody VersionProductForm versionProductForm) {
//        String productName = productService.findById(versionProductForm.getProductId()).get().getName();
//        String variantName = variantService.findById(versionProductForm.getVariantId()).get().getName();
//        String selectionName = selectionService.findById(versionProductForm.getSelectionId()).get().getName();
//        String versionProductName = productName + " " +variantName + " " +selectionName;
//        VersionProduct versionProduct = new VersionProduct(versionProductName);
//        versionProductService.save(versionProduct);
//        return new ResponseEntity("Thêm thành công! ", HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<VersionProduct> createVersion(@RequestBody VersionProduct versionProduct) {
        if (versionProductService.findByName(versionProduct.getName()) == null) {
            versionProductService.save(versionProduct);
            return new ResponseEntity("Thêm thành công! ", HttpStatus.OK);
        }
        return new ResponseEntity("Đã tồn tại!", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Void> deleteVersion(@PathVariable Long id) {
        Optional<Version> version = versionService.findById(id);
        if (version == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            versionService.delete(id);
            return new ResponseEntity("Xoá thành công", HttpStatus.OK);
        }
    }


}
