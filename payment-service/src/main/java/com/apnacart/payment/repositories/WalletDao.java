package com.apnacart.payment.repositories;

import com.apnacart.payment.entities.WalletImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<WalletImpl, Long> {
    WalletImpl findWalletImplByUserId(Long userId);
}
