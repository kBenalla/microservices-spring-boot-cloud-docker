package org.com.microserviceorder.controller;

import lombok.AllArgsConstructor;
import org.com.microserviceorder.dtos.OrderRequest;
import org.com.microserviceorder.dtos.OrderResponse;
import org.com.microserviceorder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public List<OrderResponse> findAllOrdersWithProduct(){
        return orderService.findAllOrdersWithProduct();
    }

    @GetMapping("/{id}")
    public OrderResponse findOrderWithProductById(@PathVariable Integer id){
        return orderService.findOrderWithProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse saveOrder(@RequestBody OrderRequest orderRequest){
        return orderService.saveOrder(orderRequest);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Integer id, @RequestHeader Boolean paidOrder){
        orderService.updateOrder(id,paidOrder);
    }

}
