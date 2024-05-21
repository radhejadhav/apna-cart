package com.apnacart.order.feignclients;

import java.util.List;

public class ProductRequest {
    private List<Long> productIds;
    private Long deliveryLocationId;

    public ProductRequest(List<Long> productIds) {
        this.productIds = productIds;
    }

    public ProductRequest(List<Long> productIds, Long deliveryLocationId) {
        this.productIds = productIds;
        this.deliveryLocationId = deliveryLocationId;
    }

    public Long getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
