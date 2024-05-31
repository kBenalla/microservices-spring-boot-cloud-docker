package org.com.microserviceproduct;

import org.com.microserviceproduct.entity.Product;
import org.com.microserviceproduct.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroserviceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProductApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(ProductRepository productRepository){
		return args -> {
			productRepository.save(Product.builder().name("iPhone Pro").description("Apple iPhone Pro").price(7999.99).pieces(25).build());
			productRepository.save(Product.builder().name("iPhone Pro Max").description("Apple iPhone Pro Max").price(12999.99).pieces(3).build());
			productRepository.save(Product.builder().name("iPad Pro").description("Apple iPad Pro").price(14999.99).pieces(7).build());
			productRepository.save(Product.builder().name("MacBook Pro").description("Apple MacBook Pro").price(19999.99).pieces(2).build());
		};
	}
}
