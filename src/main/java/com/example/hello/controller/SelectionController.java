package com.example.hello.controller;

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
@RequestMapping("/api/selections")
public class SelectionController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VariantService variantService;
    @Autowired
    private SelectionService selectionService;
    @GetMapping
    public ResponseEntity<Iterable<Selection>> listSelection() {
        Iterable<Selection> selections = selectionService.findAll();
        return new ResponseEntity<>(selections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Selection> detailSelection(@PathVariable Long id) {
        Optional<Selection> selection = selectionService.findById(id);
        if (selection == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(selection, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Selection> createSelection(@RequestBody Selection selection) {
        if (selection.getVariant() != null) {
            String varient = selection.getVariant().getName();
            Variant variant1 = variantService.findByName(varient);
            selection.setVariant(variant1);
        } else {
            //product.setCategory(categoryService.findById("Nguyên căn"));
        }
        selectionService.save(selection);
        return new ResponseEntity("Thêm thành công!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Selection> editSelection(@PathVariable Long id, @RequestBody Selection selection) {
        Optional<Selection> selection1 = selectionService.findById(id);
        if (selection1.isPresent()) {
            if (selection.getVariant() != null) {
                String variant = selection.getVariant().getName();
                Variant variant1 = variantService.findByName(variant);
                selection1.get().setVariant(variant1);
            }
            if (!selection.getName().equals("")) {
                selection1.get().setName(selection.getName());
            }
            selectionService.save(selection1.get());
            return new ResponseEntity("Sửa thành công!", HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSelection(@PathVariable Long id) {
        Optional<Selection> selection = selectionService.findById(id);
        if (selection == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            selectionService.delete(id);
            return new ResponseEntity("Xoá Thành Công!", HttpStatus.OK);
        }
    }
}
