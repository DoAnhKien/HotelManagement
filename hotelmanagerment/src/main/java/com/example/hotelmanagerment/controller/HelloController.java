package com.example.hotelmanagerment.controller;

import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Word";
    }

}
