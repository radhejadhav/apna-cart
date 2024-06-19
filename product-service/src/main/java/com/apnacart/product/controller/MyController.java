package com.apnacart.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/version")
    public String getVersion(){
        return "0.1.0";
    }
}
