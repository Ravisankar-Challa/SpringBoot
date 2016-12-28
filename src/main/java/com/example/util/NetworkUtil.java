package com.example.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.exception.ApplicationException;
import com.example.model.ResponseStats;
import com.example.model.Time;

import static com.example.exception.ErrorCodes.SYSTEM_EXCEPTION;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static net.logstash.logback.marker.Markers.append;

@Slf4j
@Component
@AllArgsConstructor
public class NetworkUtil {
    private static final String MILLI_SECS = "ms";
    private static final String RESPONSE = "response";
    
    private RestTemplate restClient;

    public <T> T post(final String url, final Map<String, String> urlParams,
                            final Map<String, String> headers, final Object body,
                            final Class<T> responseClazzType) {
        return invoke(HttpMethod.POST, url, urlParams, null, headers, body, responseClazzType);
    }

    public <T> T put(final String url, final Map<String, String> urlParams,
                           final Map<String, String> headers, final Object body,
                           final Class<T> responseClazzType) {
    return invoke(HttpMethod.PUT, url, urlParams, null, headers, body, responseClazzType);
    }
    
    private <T> T invoke(final HttpMethod httpMethod, final String url, 
                         final Map<String, String> urlParams,
                         final Map<String, String> queryParams,
                         final Map<String, String> headers,
                         final Object body, final Class<T> responseClazzType) {
        String uri = null;
        long requestStartTime = System.currentTimeMillis();
        try {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
            if (queryParams != null) {
                queryParams.forEach((name, value) -> uriComponentsBuilder.queryParam(name, value));
            }
            HttpHeaders httpHeaders = new HttpHeaders();
            if (headers != null) {
                headers.forEach((name, value) -> httpHeaders.add(name, value));
            }
            uri = uriComponentsBuilder.build().toString();
            ResponseEntity<T> response = restClient.exchange(uri,
                                                             httpMethod,
                                                             new HttpEntity<Object>(body, httpHeaders),
                                                             responseClazzType,
                                                             urlParams == null ? new HashMap<>() : urlParams);
           log.info(append(RESPONSE, createResponseStats(null, response.getStatusCodeValue(), uri, requestStartTime)), null);
            return response.getBody();
        } catch (HttpClientErrorException | ResourceAccessException | IllegalArgumentException exp) {
            log.error(exp.getMessage(), exp);
            if (exp instanceof HttpClientErrorException) {
                String errorResponse = ((HttpClientErrorException) exp).getResponseBodyAsString();
                int statusCode = ((HttpClientErrorException) exp).getRawStatusCode();
                log.info(append(RESPONSE, createResponseStats(errorResponse, statusCode, uri, requestStartTime)), null);
            }
            throw new ApplicationException(SYSTEM_EXCEPTION.getErrorCode(), SYSTEM_EXCEPTION.getErrorMessage());
        }
    }

    private ResponseStats createResponseStats(final String errorString, final int statusCode,
                                       final String uri, final long requestStartTime) {
        return ResponseStats.builder()
                            .status(statusCode)
                            .time(Time.builder()
                                      .value(System.currentTimeMillis() - requestStartTime)
                                      .units(MILLI_SECS)
                                      .build())
                            .uri(uri)
                            .error(errorString)
                            .build();
    }
}