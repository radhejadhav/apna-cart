package com.apnacart.order.repo;

import com.apnacart.order.entities.CartImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartImpl, Long> {

    CartImpl findCartByUserId(Long userId);
}
