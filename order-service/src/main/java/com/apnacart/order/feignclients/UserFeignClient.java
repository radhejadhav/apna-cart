package com.apnacart.order.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedHashMap;
import java.util.List;

@FeignClient(name = "users-service")
@LoadBalancerClient(name = "users-service")
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    public ResponseEntity<LinkedHashMap> getUser(@PathVariable Long id);

    @GetMapping("/user/")
    public ResponseEntity<List<LinkedHashMap<String, Object>>> getAllUser();

    @GetMapping("address/{userId}/{addressId}")
    public ResponseEntity<?> getUserAddress(@PathVariable Long userId, @PathVariable Long addressId);

}
