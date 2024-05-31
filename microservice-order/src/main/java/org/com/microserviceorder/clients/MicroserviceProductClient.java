package org.com.microserviceorder.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.com.microserviceorder.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MICROSERVICE-PRODUCT")
public interface MicroserviceProductClient {
    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "ms-product", fallbackMethod = "getDefaultProduct")
    Product findProductById(@PathVariable Integer id);

    @PutMapping("/products/{id}/pieces")
    Product updateProductByPieces(@PathVariable("id") Integer id,
                                  @RequestParam Integer numberOfPieces);

    default Product getDefaultProduct(Integer id,Exception exception){
        return Product.builder()
                .id(id)
                .description("Not Available")
                .name("Not Available")
                .build();
    }
}
