package com.apnacart.order.config;

import brave.sampler.Sampler;
import com.apnacart.order.OrderServiceApplication;
import com.apnacart.order.feignclients.UserFeignClient;
import com.apnacart.order.services.CartService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        return mapper;
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
