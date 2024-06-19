package com.apnacart.payment.controllers;

import com.apnacart.payment.dto.PaymentDto;
import com.apnacart.payment.dto.UserDetails;
import com.apnacart.payment.services.PaymentService;
import com.apnacart.payment.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping("/payment")
public class Controller {

    private PaymentService paymentService;

    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(PaymentService paymentService, UserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            UserDetails response = userService.fetchUserDetails(token);
            logger.info(response.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<?> fetchBalance(@PathVariable Long userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentService.checkBalance(userId));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/statement/{userId}")
    private ResponseEntity<?> getUserStatement(@PathVariable Long userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentService.getStatement(userId));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("")
    private ResponseEntity<?> doPayment(@RequestBody PaymentDto dto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentService.doPayment(dto));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
