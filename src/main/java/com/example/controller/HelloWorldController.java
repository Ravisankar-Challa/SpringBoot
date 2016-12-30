package com.example.controller;

import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.JavaZonedDateTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello", produces = "text/plain")
    public String sayHello() {
        log.info("Hello World!!!");
        return "Hello World!!!";
    }
    
    @GetMapping(path = "/hello1", produces = "text/plain")
    public String sayHello1() {
        log.info("Hello World!!!");
        return "Hello World!!!";
    }
    
}
