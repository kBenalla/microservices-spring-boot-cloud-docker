package org.com.microserviceorder.dtos;

import lombok.*;
import org.com.microserviceorder.model.Product;

import java.time.LocalDateTime;
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class OrderResponse {
    private Integer id;
    private Integer productId;
    private LocalDateTime createAt;
    private Integer quantity;
    private Boolean paidOrder;
    private Product product;
}
