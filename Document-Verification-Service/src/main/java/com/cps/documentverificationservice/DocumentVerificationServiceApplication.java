package com.cps.documentverificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DocumentVerificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentVerificationServiceApplication.class, args);
	}

}
