package com.apnacart.order.services;

import com.apnacart.order.entities.Order;
import com.apnacart.order.utils.OrderStatus;
import com.apnacart.order.utils.PaymentStatus;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersHistory(Long userId);
    Order getOrderByID(Long orderId);
    int updateOrderStatusById(OrderStatus status, Long userId);
    int updatePaymentStatusById(PaymentStatus status, Long userId);
}
