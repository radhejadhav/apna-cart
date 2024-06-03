package com.apnacart.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.JsonJdbcType;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_gen")
    @SequenceGenerator(name="sequence_gen",allocationSize = 1)
    private Long id;
    @NotNull
    private String label;
    private String category;
    private String description;
    @Convert(attributeName = "jsonb", converter = JsonJdbcType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    private String[] imageUrl;
    private String thumbnail;
    private Double price;
    private Double discount;
    private Double finalPrice;
}
