package com.apnacart.order.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
@LoadBalancerClient(name = "payment-service")
public interface PaymentFeignClient {

    @PostMapping("/payment")
    public ResponseEntity<?> doPayment(@RequestBody PaymentDto dto);
}
