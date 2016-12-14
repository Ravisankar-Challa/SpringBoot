package com.example.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.h2.engine.SysProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class NetworkService {
    static RestTemplate restTemplate = new RestTemplateBuilder()
                                                .setConnectTimeout(5000) 
                                                .setReadTimeout(30000)
                                                .build();
    public static void main(String[] args) {;
        System.out.println(post("https://www.google.com.au",
                new HashMap<String, String>() {{
            put("gdf","dgf");
        }}, new Test("jgfdgl")));
    }
    
    public static String post(final String url, final Map<String, String> headers, final Object body) {
        return invoke(url, HttpMethod.POST, null, headers, body);
    }
    
    public static String invoke(final String url, final HttpMethod httpMethod, 
                                final Map<String, String> queryParams,
                                final Map<String, String> headers,
                                final Object body) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        if(queryParams != null) {
            queryParams.forEach((name, value) -> uriComponentsBuilder.queryParam(name, value));
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        if(headers != null) {
            headers.forEach((name, value) -> httpHeaders.add(name, value));
        }
        return restTemplate.exchange(uriComponentsBuilder.build().toUri(), httpMethod, new HttpEntity<Object>(body, httpHeaders), String.class).getBody();
    }
}


