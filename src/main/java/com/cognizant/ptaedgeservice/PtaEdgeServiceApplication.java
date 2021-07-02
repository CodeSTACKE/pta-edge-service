package com.cognizant.ptaedgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PtaEdgeServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(PtaEdgeServiceApplication.class, args);
	}

}
