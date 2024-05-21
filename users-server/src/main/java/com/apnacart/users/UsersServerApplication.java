package com.apnacart.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class UsersServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersServerApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		return mapper;
	}
}
