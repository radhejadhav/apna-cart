package com.apnacart.products.services;

import com.apnacart.products.dao.ProductRepository;
import com.apnacart.products.dto.ProductDto;
import com.apnacart.products.entities.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ModelMapper mapper;
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private Product product;

    void init(){
        mapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Test
    void getAllProduct() {

        Page<Product> actual = null;
        Pageable pageable = PageRequest.of(1, 10);
        when(repository.findAll(pageable)).thenReturn(null);

        List<Product> error = service.getAllProduct(1);
        assertNull(error);

        actual = new PageImpl<>(List.of(product));
        when(repository.findAll(pageable)).thenReturn(actual);

        List<Product> expected = service.getAllProduct(1);
        assertEquals( expected.size(), actual.getSize());
    }

    @Test
    @DisplayName("GET: product by id")
    void getProductById() {
        Long id = 12L;
        Product actual = null;
        Product expected = null;
        when(repository.findById(id)).thenReturn(Optional.empty());
        expected = service.getProductById(id);
        assertNull(expected);

        actual = new Product();
        actual.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(actual));
        expected = service.getProductById(id);
        assertEquals(actual.getId(), expected.getId());
    }

    @Test
    void findProductByLabel() {


    }

    @Test
    @DisplayName("CREATE: new product using rest")
    void createProduct() {

        Long id = 14L;
        ProductDto dto = new ProductDto();
        dto.setLabel("Test Label");
        dto.setDiscount(234);
        dto.setMrpPrice(3000);

        Product actual = new ModelMapper().map(dto, Product.class);
        actual.setId(id);

        when(mapper.map(dto, Product.class)).thenReturn(actual);

        when(repository.save(actual)).thenReturn(actual);
        assertEquals(service.createProduct(dto), id);

        when(repository.save(actual)).thenThrow(new RuntimeException("Unable to create!!!"));
        assertNull(service.createProduct(dto));
    }

    @Test
    void updateProductByLabel() {
        ProductDto dto = new ProductDto();
        dto.setLabel("Test Label");
        Long id = 23L;

        Product actual = new ModelMapper().map(dto, Product.class);
        actual.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(actual));
        when(repository.save(actual)).thenReturn(actual);

        assertEquals(service.updateProductByLabel(id, dto), actual);

        when(repository.save(actual)).thenThrow(new RuntimeException("unable to update!!!"));
        assertNull(service.updateProductByLabel(id, dto));

    }

    @Test
    void findByCategory() {
        Long id = 23L;
        String category = "test category";

        Product actual = new Product();
        actual.setId(id);
        actual.setLabel("Test Label");
        actual.setCategories(category);

        when(repository.findProductByCategories(category)).thenReturn(Optional.of(List.of(actual)));

        List<Product> expected = service.findByCategory(category);

        assertEquals(expected.size(), 1);
        assertEquals(expected.get(0).getId(), id);

        when(repository.findProductByCategories(category)).thenReturn(Optional.empty());
        assertNull(service.findByCategory(category));

        when(repository.findProductByCategories(category)).thenThrow(new RuntimeException("Not found !!!"));
        assertNull(service.findByCategory(category));
    }
}