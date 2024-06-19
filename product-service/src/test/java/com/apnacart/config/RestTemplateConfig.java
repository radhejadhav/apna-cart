package com.apnacart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.lang.Override;

@Configuration
public class RestTemplateConfig {
    @Bean
    RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        // Rest template should not throw exceptions on server errors, but return the entity to us
//        template.setErrorHandler(new DefaultResponseErrorHandler() {
//            @Override
//            protected boolean hasError(HttpStatus statusCode) {
//                return false;
//            }
//        });
        return new RestTemplate(requestFactory);
    }
}
