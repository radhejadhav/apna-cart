package com.apnacart.order.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;

@FeignClient(name = "products-service")
@LoadBalancerClient(name = "products-service")
public interface ProductFeignClient {

    @PostMapping("/product/get")
    public ResponseEntity<List<LinkedHashMap<String, Object>>> getProductsById(@RequestBody ProductRequest request);
}
