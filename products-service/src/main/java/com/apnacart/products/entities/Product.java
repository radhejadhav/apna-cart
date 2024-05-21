package com.apnacart.products.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
    private Long id;
    @Column(nullable = false)
    private String label;
    @Lob
    @Column(columnDefinition = "BYTEA")
    private Object thumbnail;
    private double MrpPrice;
    private double discount;
    private double totalPrice;
    private String categories;
    private String description;

    public Product() {
    }

    public Product(String label, Object thumbnail, double mrpPrice, double discount, String categories, String description) {
        this.label = label;
        this.thumbnail = thumbnail;
        this.MrpPrice = mrpPrice;
        this.discount = discount;
        this.categories = categories;
        this.description = description;
        this.totalPrice = mrpPrice-discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", thumbnail=" + thumbnail +
                ", MrpPrice=" + MrpPrice +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                ", categories='" + categories + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
