package com.apnacart.claud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ClaudServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaudServerApplication.class, args);
	}

}
