package com.apnacart.payment.dto;

public class PaymentDto {
    private double amount;
    private long senderId;
    private long recipientId;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }
}
