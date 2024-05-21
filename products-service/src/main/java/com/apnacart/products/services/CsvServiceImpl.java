package com.apnacart.products.services;

import com.apnacart.products.config.CsvHelper;
import com.apnacart.products.dao.ProductRepository;
import com.apnacart.products.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class CsvServiceImpl implements CsvService{

    Logger logger = LoggerFactory.getLogger(CsvServiceImpl.class);
    private ProductRepository repository;

    public CsvServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Product> saveFile(MultipartFile file) {
        try {
            List<Product> products = CsvHelper.csvToProducts(file.getInputStream());
            products = products.stream()
                    .map(product -> CompletableFuture
                            .supplyAsync(()->{
                                if(product.getThumbnail().equals(""))
                                    product.setThumbnail(null);
                                return repository.save(product);
                            })
                    )
                    .map(f-> {
                        try {
                            return f.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
            logger.info("CSV file saved successfully!");
            return products;
        }catch (Exception e){
            logger.error("exception while saving csv data: "+ e.getMessage());
        }
        return null;
    }
}
