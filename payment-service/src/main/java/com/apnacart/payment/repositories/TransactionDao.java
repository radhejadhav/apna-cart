package com.apnacart.payment.repositories;

import com.apnacart.payment.entities.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDao extends JpaRepository<TransactionDetails, Long> {

    List<TransactionDetails> findTransactionDetailsByWalletId(Long walletId);
}
