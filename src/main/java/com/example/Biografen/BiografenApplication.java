package com.example.Biografen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class BiografenApplication {

	private static final Logger log = LoggerFactory.getLogger(BiografenApplication.class);

	public static void main(String[] args) {

		log.info("STARTING : Spring boot application starting");
		SpringApplication.run(BiografenApplication.class, args);
		log.info("STOPPED  : Spring boot application stopped");
	}

}
