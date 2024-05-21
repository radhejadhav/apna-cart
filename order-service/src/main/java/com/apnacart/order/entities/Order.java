package com.apnacart.order.entities;

import com.apnacart.order.utils.IdConverter;
import com.apnacart.order.utils.OrderStatus;
import com.apnacart.order.utils.PaymentStatus;
import com.apnacart.order.utils.PaymentType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
    private Long id;
    private Long userId;
    @Convert(converter= IdConverter.class, attributeName = "product_ids")
    private List<Long> productIds = new ArrayList<>();
    private Long deliveryLocationId;
    private double totalPrice;
    private double mrpPrice;
    private double discount;
    private double gst;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private Instant createdTimestamp;
    private Instant deliveryTimestamp;
    private PaymentType paymentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Long getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(Long deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(double mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Instant createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Instant getDeliveryTimestamp() {
        return deliveryTimestamp;
    }

    public void setDeliveryTimestamp(Instant deliveryTimestamp) {
        this.deliveryTimestamp = deliveryTimestamp;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productIds=" + productIds +
                ", deliveryLocationId=" + deliveryLocationId +
                ", totalPrice=" + totalPrice +
                ", mrpPrice=" + mrpPrice +
                ", discount=" + discount +
                ", gst=" + gst +
                ", orderStatus=" + orderStatus +
                ", paymentStatus=" + paymentStatus +
                ", createdTimestamp=" + createdTimestamp +
                ", deliveryTimestamp=" + deliveryTimestamp +
                ", paymentType=" + paymentType +
                '}';
    }
}