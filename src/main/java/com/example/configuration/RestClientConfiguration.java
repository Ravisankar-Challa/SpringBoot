package com.example.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class RestClientConfiguration {
    
    protected static final Integer DEFAULT_CONNECT_TIMEOUT = 5000;
    protected static final Integer DEFAULT_READ_TIMEOUT = 60000;
    
    private ApplicationConfiguration config;

    @Bean
    @Primary
    public RestTemplate createRestClient() {
        // Disable this if you have problems connecting through this proxy by setting enableDevelopmentProxy: false
        if (config.isEnableDevelopmentProxy()) {
            System.setProperty("https.proxyHost", config.getDevProxyHostName());
            System.setProperty("https.proxyPort", config.getDevProxyPort());
        }
        RestTemplateBuilder restTemplateBuilder =  new RestTemplateBuilder()
                // Use default timeouts in case not supplied
                .setConnectTimeout(config.getConnectTimeout() == null ? DEFAULT_CONNECT_TIMEOUT : config.getConnectTimeout())
                .setReadTimeout(config.getReadTimeout() == null ? DEFAULT_READ_TIMEOUT : config.getReadTimeout());
        
        RestTemplate restTemplate = restTemplateBuilder.build();
        if (config.isEnableRestClientRequestResponseLogging()) { 
            restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
            restTemplate.getInterceptors().add(new RestClientLoggingInterceptor());
        }
        return restTemplate;
    }
    
}
