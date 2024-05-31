package org.com.microservicepayment.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.com.microservicepayment.model.Orders;
import org.com.microservicepayment.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "MICROSERVICE-ORDER")
public interface MicroserviceOrderClient {

    @GetMapping("/orders/{id}")
    @CircuitBreaker(name = "ms-order", fallbackMethod = "getDefaultOrder")
    Orders findOrderWithProductById(@PathVariable Integer id);

    @PutMapping("/orders/{id}")
    void updateOrder(@PathVariable Integer id, @RequestHeader Boolean paidOrder);

    default Orders getDefaultOrder(Integer id, Exception exception){
        return Orders.builder()
                .product(null)
                .quantity(null)
                .build();
    }

}
