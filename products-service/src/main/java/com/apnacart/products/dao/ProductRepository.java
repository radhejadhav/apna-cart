package com.apnacart.products.dao;

import com.apnacart.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findProductByLabelAndCategoriesOrderByLabel(String label, String category);
    Optional<List<Product>> findProductByCategories(String categories);

    Product findProductByLabel(String label);
}
