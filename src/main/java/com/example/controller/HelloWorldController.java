package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ErrorResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloWorldController {
    //@ApiIgnore
    @ApiOperation(value = "Use this method to get hello world",
            notes = "Use this method for hello world notes",
            response = String.class,
            tags = { "Hello World"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success Response", response = String.class),
            @ApiResponse(code = 500, message = "Error Response", response = ErrorResponse.class)
    })
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
