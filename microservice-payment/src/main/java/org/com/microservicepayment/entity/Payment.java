package org.com.microservicepayment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.microservicepayment.enums.PaymentStatus;
import org.com.microservicepayment.enums.PaymentType;
import org.com.microservicepayment.model.Orders;
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private Integer orderId;

    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Transient
    private Orders order;
}
