package com.apnacart.order.repo;

import com.apnacart.order.entities.Order;
import com.apnacart.order.utils.OrderStatus;
import com.apnacart.order.utils.PaymentStatus;
import jakarta.ws.rs.QueryParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByUserId(Long userId);

    Order findOrderById(Long orderId);

    @Modifying
    @Transactional
    @Query(value = "update Order o set o.orderStatus=?1 where o.id=?2")
    int updateOrderStatusById(OrderStatus status, Long id);

    @Modifying
    @Transactional
    @Query(value = "update public.orders set payment_status=:status where id=:id", nativeQuery = true)
    int updatePaymentStatusById(@QueryParam("status") PaymentStatus status, @QueryParam("id") Long id);
}
