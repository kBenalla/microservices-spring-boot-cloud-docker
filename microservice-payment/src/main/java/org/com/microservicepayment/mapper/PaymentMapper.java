package org.com.microservicepayment.mapper;

import org.com.microservicepayment.dtos.PaymentRequest;
import org.com.microservicepayment.dtos.PaymentResponse;
import org.com.microservicepayment.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentMapper {
    
    
    public Payment mapToPaymentEntity(PaymentRequest paymentRequest){
        return Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .type(paymentRequest.getType())
                .build();
    }

    public PaymentResponse mapToPaymentResponse(Payment payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .type(payment.getType())
                .status(payment.getStatus())
                .order(payment.getOrder())
                .build();
    }

    public List<PaymentResponse> mapToListPaymentResponse(List<Payment> payments){
        List<PaymentResponse> paymentResponses=new ArrayList<>();
        for (Payment payment:payments){
            paymentResponses.add(mapToPaymentResponse(payment));
        }
        return paymentResponses;
    }
}
