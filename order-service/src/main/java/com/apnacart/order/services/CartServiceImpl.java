package com.apnacart.order.services;

import com.apnacart.order.entities.CartImpl;
import com.apnacart.order.entities.Order;
import com.apnacart.order.feignclients.ProductFeignClient;
import com.apnacart.order.feignclients.ProductRequest;
import com.apnacart.order.feignclients.UserFeignClient;
import com.apnacart.order.repo.CartRepository;
import com.apnacart.order.repo.OrderRepository;
import com.apnacart.order.utils.OrderStatus;
import com.apnacart.order.utils.PaymentStatus;
import com.apnacart.order.utils.PaymentType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private ModelMapper mapper;
    private ProductFeignClient productClient;
    private UserFeignClient userClient;

    public CartServiceImpl(CartRepository cartRepository, OrderRepository orderRepository, ModelMapper mapper, ProductFeignClient productClient, UserFeignClient userClient) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    @Override
    public CartImpl createCart(Long userId, Long addressId) {
        try {
            CartImpl cart = new CartImpl();
            cart.setUserId(userId);
            cart.setDeliveryLocationId(addressId);
            cartRepository.save(cart);
            return cart;
        }catch (Exception e){
            logger.error("Error creating cart: "+e.getMessage());
            return null;
        }
    }

    @Override
    public CartImpl addItemToCart(Long userId, ProductRequest productRequest) {
        CartImpl cart = null;
        try {
            cart = cartRepository.findCartByUserId(userId);
            cart.addProducts(productRequest.getProductIds());
            cart.updateCart(getItemsTotal(cart.getProductIds()), productRequest.getProductIds());

            cartRepository.save(cart);
            logger.info("Added product to the cart: "+productRequest.getProductIds() );
            return cart;
        }catch (Exception e){
            logger.error("Exception while adding product: "+e.getMessage());
            return cart;
        }
    }

    @Override
    public CartImpl removeItemFromCart(Long userId, ProductRequest productRequest) {
        CartImpl cart = null;
        try {
            cart = cartRepository.findCartByUserId(userId);
            cart.removeProducts(productRequest.getProductIds());
            cart.updateCart(getItemsTotal(cart.getProductIds()), productRequest.getProductIds());
            cartRepository.save(cart);

            logger.info("removed product from the cart: "+productRequest.getProductIds() );
            return cart;
        }catch (Exception e){
            logger.error("Exception while removing product: "+e.getMessage());
            return cart;
        }
    }

    @Override
    public CartImpl getCartData(Long userId) {
        try {
            CartImpl cart = this.cartRepository.findCartByUserId(userId);
            logger.info("Cart for user: "+userId);
            return cart;
        }catch (Exception e){
            logger.error("Exception getting cart for user: "+userId);
            throw new RuntimeException("No cart!");
        }
    }

    @Override
    public List<Order> getOrderHistory(Long userId) {
        try {
            List<Order> order = this.orderRepository.findOrderByUserId(userId);
            logger.info("return order history for user: "+ userId);
            return order;
        }catch (Exception e){
            logger.error("Exception while getting order history for user: "+userId);
            throw new RuntimeException("Exception in order history");
        }
    }

    @Override
    public CartImpl checkout(Long userId) {

        try {
            CartImpl cart = cartRepository.findCartByUserId(userId);
            if(cart.getProductIds()!=null && cart.getProductIds().size()>=1){
                Order order = mapper.map(cart, Order.class);
                order.setId(null);
                order.setUserId(userId);
                order.setOrderStatus(OrderStatus.PLACED);
                order.setOrderStatus(OrderStatus.PLACED);
                order.setPaymentStatus(PaymentStatus.PENDING);
                order.setPaymentType(PaymentType.CASH);
                order.setCreatedTimestamp(Instant.now());
                order.setDeliveryTimestamp(Instant.now().plus(Duration.ofDays(7)));

                orderRepository.save(order);
                logger.info("Order Placed successfully!!! "+order);
                cart.checkoutOrEmpty();
                cartRepository.save(cart);
                logger.info("updated cart");
                return cart;
            }else throw new RuntimeException("Cart is empty!");
        }catch (Exception e){
            logger.error("Order Not Place! "+e.getMessage());
            throw new RuntimeException("Order Not Place: "+ e.getMessage());
        }
    }

    public LinkedHashMap<String, Object> getItemsTotal(List<Long> productIds ){
        try {
            ResponseEntity<List<LinkedHashMap<String, Object>>> response = productClient
                    .getProductsById(new ProductRequest(productIds));

            return Objects.requireNonNull(response.getBody())
                    .stream()
                    .flatMap(m->m.entrySet()
                            .stream()
                            .filter(entry-> List
                                    .of("totalPrice", "discount", "mrpPrice")
                                    .contains(entry.getKey())
                            ))
                    .collect(Collectors
                            .toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (a1, a2) -> (double)a1+(double)a2,
                                    LinkedHashMap::new
                            )
                    );
        }catch (Exception e){
            logger.error("Exception while getting product cost: "+e.getMessage());
            return null;
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() throws Exception {
        try {
            ResponseEntity<List<LinkedHashMap<String, Object>>> response = userClient.getAllUser();

            logger.info("Creating carts...");
            if(response.getStatusCode().value()==200){
                Objects.requireNonNull(response.getBody()).forEach(user->{
                    Long id = Integer.toUnsignedLong((int)user.get("id"));// new Long((int)user.get("id"));
                    List<LinkedHashMap<String, Object>> addresses =(List<LinkedHashMap<String, Object>>) user.get("addresses");
                    Long addressId = Integer.toUnsignedLong((int)addresses.get(0).get("id"));
                    createCart(id, addressId);
                    logger.info("Cart created for user id: "+ id);
                });
            }
        }catch (Exception e){
            logger.error("Exception while creating cart: "+e.getMessage());
        }
    }
}
