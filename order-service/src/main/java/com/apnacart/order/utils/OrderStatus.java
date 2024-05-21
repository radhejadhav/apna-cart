package com.apnacart.order.utils;

public enum OrderStatus {
    PLACED("placed"),
    PROGRESS("in_progress"),
    PENDING("pending"),
    DELIVERED("delivered");
    OrderStatus(String s) {
    }
}
