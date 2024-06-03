package com.apnacart.product.services;


import com.apnacart.product.dto.ProductDto;
import com.apnacart.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);
    List<Product> getAllProduct(int pageNo, int recordCount);
    List<Product> findProduct(String searchString);
    Product createProduct(Product dto);
    Product updateProduct(Product dto, Long id);
    Long deleteProduct(Long id);
}
