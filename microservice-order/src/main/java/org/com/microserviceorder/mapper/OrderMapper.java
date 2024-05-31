package org.com.microserviceorder.mapper;

import org.com.microserviceorder.dtos.OrderRequest;
import org.com.microserviceorder.dtos.OrderResponse;
import org.com.microserviceorder.entity.Orders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {


    public Orders mapToOrderEntity(OrderRequest orderRequest){
        return Orders.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .build();
    }

    public OrderResponse mapToOrderResponse(Orders orders){

        return OrderResponse.builder()
                .id(orders.getId())
                .productId(orders.getProductId())
                .quantity(orders.getQuantity())
                .createAt(orders.getCreateAt())
                .paidOrder(orders.getPaidOrder())
                .product(orders.getProduct())
                .build();
    }

    public List<OrderResponse> mapToListOrderResponse(List<Orders> ordersList){
        List<OrderResponse> orderResponses=new ArrayList<>();
        for (Orders orders:ordersList){
            orderResponses.add(mapToOrderResponse(orders));
        }
        return orderResponses;
    }
}
