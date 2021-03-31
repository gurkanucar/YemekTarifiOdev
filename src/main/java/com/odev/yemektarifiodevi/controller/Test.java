package com.odev.yemektarifiodevi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Test {
    @GetMapping
    public String deneme(){
        return "Herkes";
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
    @GetMapping("/ek")
    public String ek(){
        return "ek mek";
    }

    @GetMapping("/mkayac")
    public String mkayac(){
        return "Selam mkayac";
    }

    @GetMapping("/ozan")
    public String ozan(){
        return "ozan back-end'te marka";
    }



}
