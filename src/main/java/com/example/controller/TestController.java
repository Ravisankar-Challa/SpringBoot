package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Request;

import static com.example.util.Constants.APPLICATION_JSON;

@RestController
public class TestController {

    @PostMapping(path = "/hello", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    public String post(final @RequestBody Request request) {
        System.out.println(request.getTest());
        System.out.println("******************************************88");
        return "{\"test\":\"hello\"}";
    }
    
}
