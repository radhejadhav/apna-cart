package com.apnacart.product.controller;

import com.apnacart.product.entity.Product;
import com.apnacart.product.services.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping("/product")
public class Controller {

    @Autowired
    private ProductService service;

    @GetMapping("/all/{count}/{pageNo}")
    public ResponseEntity<?> getAllProduct(@PathVariable int count, @PathVariable int pageNo){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProduct(pageNo, count));
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<?> findProduct(@PathVariable String search){
        return ResponseEntity.status(HttpStatus.OK).body(service.findProduct(search));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductById((long) id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.OK).body(service.createProduct(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateProduct(product, (long) id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteProduct((long) id));
    }
}
