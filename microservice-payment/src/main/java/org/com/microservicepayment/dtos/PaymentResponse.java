package org.com.microservicepayment.dtos;

import lombok.*;
import org.com.microservicepayment.enums.PaymentStatus;
import org.com.microservicepayment.enums.PaymentType;
import org.com.microservicepayment.model.Orders;
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class PaymentResponse {
    private Integer id;
    private Integer orderId;
    private Double amount;
    private PaymentType type;
    private PaymentStatus status;
    private Orders order;
}
