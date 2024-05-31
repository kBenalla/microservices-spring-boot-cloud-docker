package org.com.microserviceorder.service;

import lombok.AllArgsConstructor;
import org.com.microserviceorder.clients.MicroserviceProductClient;
import org.com.microserviceorder.dtos.OrderRequest;
import org.com.microserviceorder.dtos.OrderResponse;
import org.com.microserviceorder.entity.Orders;
import org.com.microserviceorder.exceptions.OrderNotFoundException;
import org.com.microserviceorder.mapper.OrderMapper;
import org.com.microserviceorder.model.Product;
import org.com.microserviceorder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private MicroserviceProductClient productClient;

    public Orders findOrderEntityById(Integer id){
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Orders saveOrderEntity(OrderRequest orderRequest){
        Orders order=orderMapper.mapToOrderEntity(orderRequest);
        order.setCreateAt(LocalDateTime.now());
        order.setPaidOrder(false);
        return orderRepository.save(order);

    }

    public List<OrderResponse> findAllOrdersWithProduct(){
        List<Orders> ordersList=orderRepository.findAll();
        if (ordersList.isEmpty()) throw new OrderNotFoundException();
        ordersList.forEach(orders -> {
            orders.setProduct(productClient.findProductById(orders.getProductId()));
        });
        return orderMapper.mapToListOrderResponse(ordersList);
    }

    public OrderResponse findOrderWithProductById(Integer id){
        Orders orders=findOrderEntityById(id);
        Product product=productClient.findProductById(orders.getProductId());
        orders.setProduct(product);
        return orderMapper.mapToOrderResponse(orders);
    }

    public OrderResponse saveOrder(OrderRequest orderRequest){
        Product product=productClient.findProductById(orderRequest.getProductId());
        Product updateProductByPieces
                =productClient.updateProductByPieces(product.getId(), orderRequest.getQuantity());
        Orders savedOrder=saveOrderEntity(orderRequest);
        savedOrder.setProduct(updateProductByPieces);
        return orderMapper.mapToOrderResponse(savedOrder);
    }

    public void updateOrder(Integer id,Boolean paidOrder){
        Orders existingCommand=findOrderEntityById(id);
        existingCommand.setPaidOrder(paidOrder);
        orderRepository.save(existingCommand);
    }




}
