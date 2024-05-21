package com.apnacart.order.controllers;

import com.apnacart.order.dto.OrderDto;
import com.apnacart.order.entities.CartImpl;
import com.apnacart.order.feignclients.ProductRequest;
import com.apnacart.order.services.CartService;
import com.apnacart.order.services.OrderService;
import com.apnacart.order.utils.OrderStatus;
import com.apnacart.order.utils.PaymentStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class Controller {

    private OrderService orderService;
    private CartService cartService;

    public Controller(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<?> placeOrder(@PathVariable Long userId){

        try {
            CartImpl cart = cartService.checkout(userId);
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/add/{userId}")
    public ResponseEntity<?> addProduct(@PathVariable Long userId,@RequestBody ProductRequest productRequest){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cartService.addItemToCart(userId, productRequest));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/remove/{userId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long userId,@RequestBody ProductRequest productRequest){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cartService.removeItemFromCart(userId, productRequest));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getOrderHistory(@PathVariable Long userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.cartService.getOrderHistory(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartData(@PathVariable Long userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.cartService.getCartData(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.orderService.getOrderByID(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/status/{orderId}")
    public ResponseEntity<?> updateOrderStatusByOrderId(@RequestParam OrderStatus status, @PathVariable Long orderId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderStatusById(status, orderId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping("/payment/status/{orderId}")
    public ResponseEntity<?> updatePaymentStatusByOrderId(@RequestParam PaymentStatus status, @PathVariable Long orderId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updatePaymentStatusById(status, orderId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
