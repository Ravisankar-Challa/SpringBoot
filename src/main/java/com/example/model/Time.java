package com.example.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Time {
    public long value;
    public String units;
}
