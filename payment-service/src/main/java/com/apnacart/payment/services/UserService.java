package com.apnacart.payment.services;

import com.apnacart.payment.dto.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;


//    @PreAuthorize("hasAnyRole('admin')")
    public UserDetails fetchUserDetails(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Accept", "*/*");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8282/realms/master/protocol/openid-connect/userinfo",
                HttpMethod.GET,
                requestEntity,
                UserDetails.class
                ).getBody();
    }
}
