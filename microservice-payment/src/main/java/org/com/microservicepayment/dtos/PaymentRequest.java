package org.com.microservicepayment.dtos;

import lombok.Getter;
import lombok.Setter;
import org.com.microservicepayment.enums.PaymentType;
@Getter @Setter
public class PaymentRequest {

    private Integer orderId;
    private PaymentType type;
}
