package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class testController {

    @GetMapping("/health")
    public String hello() {
        return "Spring Boot Server is running...";
    }
}
