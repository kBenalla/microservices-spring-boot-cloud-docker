package org.com.microservicepayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicePaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePaymentApplication.class, args);
	}

}
