package com.example.util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Test {
    private String hello;

    public Test(String hello) {
        super();
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
    
    
}
