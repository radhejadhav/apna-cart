package com.apnacart.order.feignclients;

public class PaymentDto {
    private double amount;
    private long senderId;
    private long recipientId;

    public PaymentDto(double amount, long senderId, long recipientId) {
        this.amount = amount;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public double getAmount() {
        return amount;
    }

    public long getSenderId() {
        return senderId;
    }

    public long getRecipientId() {
        return recipientId;
    }
}
