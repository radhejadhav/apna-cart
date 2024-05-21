package com.apnacart.order.services;

import com.apnacart.order.entities.CartImpl;
import com.apnacart.order.entities.Order;
import com.apnacart.order.feignclients.ProductRequest;

import java.util.List;

public interface CartService {
    CartImpl addItemToCart(Long userId, ProductRequest productRequest);
    CartImpl removeItemFromCart(Long userId, ProductRequest productRequest);
    CartImpl checkout(Long userId);

    CartImpl createCart(Long userId, Long addressId);

    CartImpl getCartData(Long userId);
    List<Order> getOrderHistory(Long userId);

}
