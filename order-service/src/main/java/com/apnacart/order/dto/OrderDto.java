package com.apnacart.order.dto;

import com.apnacart.order.utils.OrderStatus;
import com.apnacart.order.utils.PaymentStatus;
import com.apnacart.order.utils.PaymentType;

import java.util.List;

public class OrderDto {
    private Long deliveryLocationId;
    private OrderStatus orderStatus;
    private List<Long> productIds;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;

    public Long getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(Long deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
