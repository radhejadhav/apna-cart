package com.apnacart.product.controller;

import com.apnacart.product.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Authorization")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService productService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(HttpServletRequest request){
        ResponseEntity response = null;
        try {
            String token = request.getHeader("Authorization");

            logger.info("getting user details");

            response = productService.fetchUserDetails(token);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
