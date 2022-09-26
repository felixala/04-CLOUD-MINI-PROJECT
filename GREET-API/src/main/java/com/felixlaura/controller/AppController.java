package com.felixlaura.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/greet")
    public String getMessage(){
        String msg = "Hello World";

        return msg;
    }
}
