package com.apnacart.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper(){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		return mapper;
	}
}
