package com.apnacart.order.services;

import com.apnacart.order.entities.Order;
import com.apnacart.order.feignclients.ProductFeignClient;
import com.apnacart.order.feignclients.UserFeignClient;
import com.apnacart.order.repo.OrderRepository;
import com.apnacart.order.utils.OrderStatus;
import com.apnacart.order.utils.PaymentStatus;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderRepository repository;

    private ModelMapper mapper;
    private UserFeignClient userClient;

    private ProductFeignClient productClient;

    public OrderServiceImpl(OrderRepository repository, UserFeignClient userClient, ProductFeignClient productClient, ModelMapper mapper) {
        this.repository = repository;
        this.userClient = userClient;
        this.productClient = productClient;
        this.mapper = mapper;
    }

    @Override
    public List<Order> getOrdersHistory(Long userId) {
        try {
            List<Order> orders = repository.findOrderByUserId(userId);
            logger.info("Found order history: "+ orders.size());
            return orders;
        }catch (Exception e){
            logger.error("Exception while getting order history: "+e.getMessage());
            throw new RuntimeException("Exception while getting order history.");
        }
    }

    @Override
    public Order getOrderByID(Long orderId) {
        try {
            repository.findOrderById(orderId);
        }catch (Exception e){
            logger.error("Exception while getting order: "+ orderId+": "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateOrderStatusById(OrderStatus status, Long userId) {
        try {
            return repository.updateOrderStatusById(status, userId);
        }catch (Exception e){
            logger.error("Exception while updating order status: "+ e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int updatePaymentStatusById(PaymentStatus status, Long userId) {
        try {
            return repository.updatePaymentStatusById(status, userId);
        }catch (Exception e){
            logger.error("Exception while updating payment status: "+ e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
