package com.auth.apna;

import com.auth.apna.dto.UserDto;
import com.auth.apna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class ApnaApplication {

	@Autowired
	private UserService service;

	public static void main(String[] args) {
		SpringApplication.run(ApnaApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runner(){
		UserDto dto = new UserDto();

		dto.setName("Radheshyam");
		dto.setUsername("radhejadhav");
		dto.setPassword("Radhe@2998");
		dto.setRoles(List.of("ADMIN","USER"));
		service.createUser(dto);
	}
}
