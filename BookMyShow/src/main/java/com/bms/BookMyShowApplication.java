package com.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bms.repository.UserRepository;

@SpringBootApplication
@AutoConfigurationPackage
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@ComponentScan("com.bms.controller")
@ComponentScan("com.bms.service")
@EntityScan("com.bms.model")
public class BookMyShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}
}
