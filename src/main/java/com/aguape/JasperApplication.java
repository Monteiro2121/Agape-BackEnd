package com.aguape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.aguape")
public class JasperApplication {
	public static void main(String[] args) {
		SpringApplication.run(JasperApplication.class, args);
	}
}
