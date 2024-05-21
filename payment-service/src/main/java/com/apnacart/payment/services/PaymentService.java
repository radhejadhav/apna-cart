package com.apnacart.payment.services;


import com.apnacart.payment.dto.PaymentDto;
import com.apnacart.payment.entities.TransactionDetails;

import java.util.List;

public interface PaymentService {

    double checkBalance(Long userId);
    boolean doPayment(PaymentDto paymentDto);
    List<TransactionDetails> getStatement(Long userId);
}
