package com.example.hello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/hello/")
public class    HelloWorld {
    @GetMapping
    public ResponseEntity<String > sayHello(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
