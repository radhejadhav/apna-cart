package com.apnacart.payment;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {

	public static void main(String[] args) {
//		ApplicationContext context = SpringApplication.run(PaymentServiceApplication.class, args);
		new SpringApplicationBuilder(PaymentServiceApplication.class)
				.lazyInitialization(true)
				.build(args)
				.run();
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
