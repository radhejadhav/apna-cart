package com.apnacart.products.services;

import com.apnacart.products.dto.ProductDto;
import com.apnacart.products.dto.ProductRequestDto;
import com.apnacart.products.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct(int pageNumber);
    Product getProductById(Long id);
    List<Product> findProductByLabel(String label);
    Long createProduct(ProductDto productDto);

    Product updateProductByLabel(Long id, ProductDto productDto );
    List<Product> findByCategory(String category);

    List<Product> getMultipleProductById(ProductRequestDto requestDto);
}
