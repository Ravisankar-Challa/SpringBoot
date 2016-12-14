package com.example.util;

import com.fasterxml.jackson.core.JsonGenerator;

import net.logstash.logback.decorate.JsonGeneratorDecorator;

public class PrettyPrintingJsonDecorator implements JsonGeneratorDecorator {
    @Override
    public JsonGenerator decorate(JsonGenerator generator) {
        return generator.useDefaultPrettyPrinter();
    }
}
