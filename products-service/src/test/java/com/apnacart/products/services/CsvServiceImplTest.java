package com.apnacart.products.services;

import com.apnacart.products.dao.ProductRepository;
import com.apnacart.products.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CsvServiceImplTest {

    @Mock
    private ModelMapper mapper;

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private CsvServiceImpl service;

    @Test
    void saveFile() {
        Product actual = new Product();

        String data = "Label,Thumbnail,mrpPrice,discount,categories,description\n" +
                "Running Shoes,,2999,500,footware,Nice shoes for running.";
        MultipartFile file = new MockMultipartFile("data", "filename.csv", "text/plain", data.getBytes());
        assertNotNull(service.saveFile(file));

        when(repository.save(actual)).thenThrow(new RuntimeException("Unable to save!!!"));
        assertNull(service.saveFile(file));
    }
}