package com.apnacart.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;


    public ResponseEntity<?> fetchUserDetails(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Accept", "*/*");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8282/realms/master/protocol/openid-connect/userinfo",
                HttpMethod.GET,
                requestEntity,
                String.class
                );
    }
}
