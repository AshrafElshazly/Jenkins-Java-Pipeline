package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureOneController {

    @GetMapping("/")
    public String Feature() {
        return "Hello from Feature number 1";
    }
}
