package com.apnacart.products.services;

import com.apnacart.products.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvService {

    List<Product> saveFile(MultipartFile file);
}
