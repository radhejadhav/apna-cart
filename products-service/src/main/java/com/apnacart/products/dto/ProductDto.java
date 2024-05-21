package com.apnacart.products.dto;

public class ProductDto {

    private String label;
    private Object thumbnail;
    private double MrpPrice;
    private double discount;
    private String categories;
    private String description;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getMrpPrice() {
        return MrpPrice;
    }

    public void setMrpPrice(double mrpPrice) {
        MrpPrice = mrpPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
