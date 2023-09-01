package com.cps.claimdisbursementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClaimDisbursementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimDisbursementServiceApplication.class, args);
	}

}
