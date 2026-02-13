package com.quetzal.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
	    "com.quetzal.restaurant",
	    "com.quetzal.restaurant.globals"
	})
	@EnableJpaRepositories(basePackages = {
	    "com.quetzal.restaurant.repository",
	    "com.quetzal.restaurant.globals.provider"
	})
	@EntityScan(basePackages = {
	    "com.quetzal.restaurant.globals.model" // <--- ¡Asegúrate de que este sea el paquete exacto de RolePermission!
	})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
