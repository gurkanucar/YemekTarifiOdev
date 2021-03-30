package com.odev.yemektarifiodevi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Test {
    @GetMapping
    public String deneme(){
        return "MEHMET";
    }

    @GetMapping("/grkn")
    public String grkn(){
        return "selam grkn";
    }
    @GetMapping("/atkn")
    public String atkn(){
        return "hi atkn";
    }
    @GetMapping("/tburakg")
    public String tburakg(){
        return "hi tburakg";
    }


}
