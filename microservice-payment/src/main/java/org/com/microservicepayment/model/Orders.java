package org.com.microservicepayment.model;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Orders {
    private Integer id;
    private Integer productId;
    private LocalDateTime createAt;
    private Integer quantity;
    private Boolean paidOrder;
    private Product product;
}
