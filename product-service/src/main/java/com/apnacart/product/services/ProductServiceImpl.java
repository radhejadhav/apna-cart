package com.apnacart.product.services;

import com.apnacart.product.dao.ProductRepository;
import com.apnacart.product.entity.Product;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public Product getProductById(Long id) {
        return repository
                .findProductById(id)
                .orElseThrow(()->new NotFoundException("product not found!!!"));
    }

    @Override
    public List<Product> getAllProduct(int pageNo, int recordCount) {
        Pageable pageable = Pageable.ofSize(recordCount).withPage(pageNo);
        return repository.findAll(pageable).get().collect(Collectors.toList());
    }

    @Override
    public List<Product> findProduct(String searchString) {
        Pageable pageable = Pageable.ofSize(10);
        return repository.findProductByLabel(searchString, pageable)
                .get();
    }

    @Override
    public Product createProduct(Product dto) {
        return repository.save(dto);
    }

    @Override
    public Product updateProduct(Product dto, Long id) {
        Product persistedProduct = this.getProductById(id);
        ModelMapper mapper = new ModelMapper();

        mapper.map(persistedProduct, dto);
        repository.save(dto);
        return dto;
    }

    @Override
    public Long deleteProduct(Long id) {
        repository.deleteById(id);
        return id;
    }
}
