package com.apnacart.order.entities;

import java.util.List;
import java.util.Map;

public interface Cart {
    void removeProducts(List<Long> productIds);
    void addProducts(List<Long> productIds);
    void checkoutOrEmpty();
    void updateCart(Map<String, Object> data, List<Long> productIds);
}
