package com.example.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseStats {
    public String uri;
    public int status;
    public Time time;
    public String error;
}
