package com.apnacart.products.controllers;

import com.apnacart.products.ProductsServiceApplication;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@SpringBootApplication(scanBasePackageClasses = ProductsServiceApplication.class)
class ProductControllerTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webContext;

    protected void setUp(){
        System.out.println("TESTS");
        mvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void getAllProduct() throws Exception {

        String uri = "/product";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(status, 200);
        String contents = result.getResponse().getContentAsString();

        System.out.println(contents);

    }

    @Test
    void getProductById() {
    }

    @Test
    void updateProductById() {
    }

    @Test
    void uploadCsv() {
    }
}