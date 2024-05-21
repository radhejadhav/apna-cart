package com.apnacart.products.services;

import com.apnacart.products.dto.ProductDto;
import com.apnacart.products.dao.ProductRepository;
import com.apnacart.products.dto.ProductRequestDto;
import com.apnacart.products.entities.Product;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ModelMapper mapper;
    private ProductRepository repository;

    public ProductServiceImpl(ModelMapper mapper, ProductRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProduct(int pageNumber) {
        Page<Product> productList = null;
        try {
            Pageable pageable = PageRequest.of(pageNumber, 10);
            productList = repository.findAll(pageable);
            logger.info("found product of size:"+ productList.getTotalElements());
        }catch (Exception e){
            logger.error("Exception while getting products: "+ e.getMessage());
        }
        if(productList != null){
            return productList.toList();
        }else return null;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = null;
        try {
            Optional<Product> productOptional = repository.findById(id);
            product = productOptional.orElseGet(null);
            logger.info("found product with id:"+ product.getId());
        }catch (Exception e){
            logger.error("Exception while getting products: "+ e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> findProductByLabel(String label) {

        return null;
    }

    @Override
    public Long createProduct(ProductDto productDto) {
        Product product = null;
        try {
            product = mapper.map(productDto, Product.class);
            product.setTotalPrice(productDto.getMrpPrice()-productDto.getDiscount());
            repository.save(product);
            logger.info("New Product created in db:"+ product.getLabel());
            return product.getId();
        }catch (Exception e){
            logger.error("Exception while creating product: "+e.getMessage());
        }
        return null;
    }

    @Override
    public Product updateProductByLabel(Long id, ProductDto productDto ){
        Product updatedProduct = null;
        try {
            Optional<Product> persistedProduct = repository.findById(id);

            updatedProduct = persistedProduct.orElse(null);
            if(updatedProduct!=null){
                mapper.map(productDto, updatedProduct);
                logger.info("updated product: "+ updatedProduct.getId());
                repository.save(updatedProduct);
                return updatedProduct;
            }
        }catch (Exception e){
            logger.info("Exception while updating product");
        }
        return null;
    }

    @Override
    public List<Product> findByCategory(String category) {
        List<Product> products = null;
        try {
            products = repository.findProductByCategories(category).orElse(null);
            if(products == null)
                logger.warn("No product for the category: "+ category);
            else {
                logger.info("Product found: "+products.size());
                return products;
            }
        }catch (Exception e){
            logger.error("Exception while getting products: "+ e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getMultipleProductById(ProductRequestDto requestDto) {
        try {
            List<Product> productList = requestDto.getProductIds()
                    .stream()
                    .map(id -> repository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .toList();
            logger.info("Found products:"+ productList.size());
            return productList;
        }catch (Exception e){
            logger.error("Exception: "+e.getMessage());
            throw new RuntimeException("Error getting products");
        }
    }
}
