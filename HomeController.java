package com.example.demo5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/view")
    public String view() {
        return "view";
    }
}