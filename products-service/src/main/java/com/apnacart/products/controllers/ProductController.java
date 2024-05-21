package com.apnacart.products.controllers;

import com.apnacart.products.config.CsvHelper;
import com.apnacart.products.dto.ProductDto;
import com.apnacart.products.dto.ProductRequestDto;
import com.apnacart.products.entities.Product;
import com.apnacart.products.services.CsvService;
import com.apnacart.products.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    private CsvService csvService;

    private ProductService productService;

    public ProductController(CsvService csvService, ProductService productService) {
        this.csvService = csvService;
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(@RequestParam("page") Integer page){
        List<Product> productList = productService.getAllProduct(page);
        if(productList!=null&& productList.size()>0){
            return ResponseEntity.status(HttpStatus.FOUND).body(productList);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product Found!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if(product!=null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable Long id, @RequestBody ProductDto productDto ){
        Product updatedProduct = productService.updateProductByLabel(id, productDto);
        if(updatedProduct!=null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        }else return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Unable to update!");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCsv(@RequestParam MultipartFile file){
        if(CsvHelper.hasCSVFormat(file)){
            return ResponseEntity.status(HttpStatus.OK).body(csvService.saveFile(file));
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("corrupted file format!");
    }

    @PostMapping("/get")
    public ResponseEntity<?> getProductByIds(@RequestBody ProductRequestDto requestDto){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(productService.getMultipleProductById(requestDto));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }

}
