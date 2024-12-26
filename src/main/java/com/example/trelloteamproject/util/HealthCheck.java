package com.example.trelloteamproject.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/home")
    public String healthCheck() {
        return "OK";
    }
}
