package org.com.microserviceorder.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.microserviceorder.model.Product;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Orders {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private LocalDateTime createAt;
    private Integer quantity;
    private Boolean paidOrder;
    @Transient
    private Product product;
}
