package com.example.configuration;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "qepp")
public class ApplicationConfiguration {
    private String encryptionKey;
    private String initVector;
    private Integer connectTimeout;
    private Integer readTimeout;
    private boolean enableDevelopmentProxy;
    private String devProxyHostName;
    private String devProxyPort;
    private boolean enableRestClientRequestResponseLogging;
}
