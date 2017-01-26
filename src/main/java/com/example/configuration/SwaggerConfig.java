package com.example.configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Test API")
                                   .description("Description goes here")
                                   .license("Some Company")
                                   .licenseUrl("")
                                   .termsOfServiceUrl("")
                                   .version("v1")
                                   .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                                                      .select()
                                                      .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                                                      .paths(Predicates.not(PathSelectors.regex("/error.*")))
                                                      .build()
                                                      .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                                                      .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                                                      .apiInfo(apiInfo());
    }

}
