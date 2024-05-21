package com.apnacart.payment.entities;

public interface Wallet {

    double credit(Long senderId, Double amount);
    double debit(Long recipientId, Double amount);
    double checkBalance();
}
