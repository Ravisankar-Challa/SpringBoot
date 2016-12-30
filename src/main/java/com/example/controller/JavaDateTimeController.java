package com.example.controller;

import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.JavaZonedDateTime;

@RestController
public class JavaDateTimeController {
    
    @GetMapping(path = "java8zoneddatetimetest", produces = "application/json")
    public JavaZonedDateTime getUTCTime() {
        return JavaZonedDateTime.builder().date(ZonedDateTime.now()).build();
    }
    
    @PostMapping(path = "java8zoneddatetimetest", consumes = "application/json", produces = "application/json")
    public JavaZonedDateTime acceptUTCTime(@RequestBody JavaZonedDateTime javaZonedDateTime) {
        return javaZonedDateTime;
    }
}
