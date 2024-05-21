package com.apnacart.order.entities;

import com.apnacart.order.utils.IdConverter;
import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "cart")
public class CartImpl implements Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
    private Long id;
    private Long userId;
    @Convert(converter= IdConverter.class, attributeName = "product_ids")
    private List<Long> productIds = new ArrayList<>();
    private Long deliveryLocationId;
    private double totalPrice;
    private double mrpPrice;
    private double discount;

    private double gst;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Long getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(Long deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(double mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void removeProducts(List<Long> productIds) {
        this.productIds = this.productIds.stream()
                .filter(id->!productIds.contains(id))
                .collect(Collectors.toList());
    }

    @Override
    public void checkoutOrEmpty() {
        this.productIds = new ArrayList<>();
        this.setDiscount(0);
        this.setTotalPrice(0);
        this.setMrpPrice(0);
        this.setGst(0);
    }

    @Override
    public void addProducts(List<Long> productIds) {
        if(this.productIds==null){
            this.productIds = new ArrayList<>();
        }
        this.productIds.addAll(productIds);
    }

    @Override
    public void updateCart(Map<String, Object> data, List<Long> productIds) {
        DecimalFormat format = new DecimalFormat("#.##");
        if(this.productIds.isEmpty()){
            this.checkoutOrEmpty();
        }else {
            this.discount = (double)data.get("discount");
            this.mrpPrice = (double)data.get("mrpPrice");
            this.gst = Double.parseDouble(format.format(0.09 * (double)data.get("mrpPrice")));
            this.totalPrice = (this.mrpPrice - this.discount) + this.gst;
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", productIds=" + productIds +
                '}';
    }
}
