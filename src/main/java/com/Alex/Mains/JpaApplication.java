package com.Alex.Mains;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.Alex.Mains", "com.Alex.UserPackage", "com.Alex.Flights", "com.Alex.Security", "com.Alex.Validations"
})
@EntityScan( basePackages = {"com.Alex.UserPackage", "com.Alex.Flights"})
@EnableJpaRepositories({"com.Alex.UserPackage", "com.Alex.Flights"})
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

}
