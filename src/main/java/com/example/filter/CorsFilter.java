package com.example.filter;

import java.io.IOException;
import static java.net.HttpURLConnection.HTTP_OK;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(2)
@Component
public class CorsFilter extends OncePerRequestFilter {

    public static final String OPTIONS = "OPTIONS";
    public static final String ORIGIN = "ORIGIN";
    public static final String WEBSITE_REGEX_EXPRESSION = "^https?:\\/\\/([a-zA-Z\\d-]+\\.){0,}goole(\\.com|\\.com.au)(.*?)$";

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        final String origin = request.getHeader(ORIGIN);
        if (origin != null && (origin.matches(WEBSITE_REGEX_EXPRESSION) || origin.contains("localhost") || origin.contains("petstore"))) {
            if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
                log.info("Received pre-flight options request from origin : {} ", origin);
                response.addHeader("Access-Control-Allow-Origin", origin);
                response.addHeader("Access-Control-Allow-Headers",
                                   "origin, content-type, accept, x-test");
                response.addHeader("Access-Control-Allow-Credentials", "false");
                response.addHeader("Access-Control-Allow-Methods", "GET, POST");
                response.setStatus(HTTP_OK);
            } else {
                response.addHeader("Access-Control-Allow-Origin", origin);
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
