package com.apnacart.payment.services;


import com.apnacart.payment.dto.PaymentDto;
import com.apnacart.payment.entities.Transaction;
import com.apnacart.payment.entities.TransactionDetails;

import java.util.List;

public interface PaymentService {

    double checkBalance(Long userId);
    boolean doPayment(PaymentDto paymentDto);
    List<TransactionDetails> getStatement(Long userId);

    Transaction credit(Long userId, double amount);
    Transaction debit(Long userId, double amount);
}
