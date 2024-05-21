package com.apnacart.payment.controllers;

import com.apnacart.payment.dto.PaymentDto;
import com.apnacart.payment.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class Controller {

    private PaymentService paymentService;

    public Controller(PaymentService paymentService) {
        this.paymentService = paymentService;
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
