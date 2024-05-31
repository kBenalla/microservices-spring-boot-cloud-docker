package org.com.microservicepayment.service;

import lombok.AllArgsConstructor;
import org.com.microservicepayment.clients.MicroserviceOrderClient;
import org.com.microservicepayment.dtos.PaymentRequest;
import org.com.microservicepayment.dtos.PaymentResponse;
import org.com.microservicepayment.entity.Payment;
import org.com.microservicepayment.enums.PaymentStatus;
import org.com.microservicepayment.exceptions.ExistingPaymentException;
import org.com.microservicepayment.exceptions.PaymentNotFoundException;
import org.com.microservicepayment.mapper.PaymentMapper;
import org.com.microservicepayment.model.Orders;
import org.com.microservicepayment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private MicroserviceOrderClient orderClient;


    public List<PaymentResponse> findAllPaymentsWithOrder(){
        List<Payment> paymentList=paymentRepository.findAll();
        if (paymentList.isEmpty()) throw new PaymentNotFoundException();
        paymentList.forEach(payment -> {
            payment.setOrder(orderClient.findOrderWithProductById(payment.getOrderId()));
        });
        return paymentMapper.mapToListPaymentResponse(paymentList);

    }

    public Payment findPaymentEntityById(Integer id){
        return paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
    }

    public PaymentResponse findPaymentWithOrderById(Integer id){
        Payment payment=findPaymentEntityById(id);
        Orders orders=orderClient.findOrderWithProductById(payment.getOrderId());
        payment.setOrder(orders);
        return paymentMapper.mapToPaymentResponse(payment);
    }

    public PaymentResponse savePayment(PaymentRequest paymentRequest){
        Optional<Payment> existingPayment=paymentRepository.findByOrderId(paymentRequest.getOrderId());
        if (existingPayment.isPresent()) throw new ExistingPaymentException("This order is already paid");

        Orders orders=orderClient.findOrderWithProductById(paymentRequest.getOrderId());

        double  amountProduct=orders.getProduct().getPrice();
        double totalAmount=amountProduct*orders.getQuantity();
        Payment savedPayment=paymentRepository.save(Payment.builder()
                                    .type(paymentRequest.getType())
                                    .status(PaymentStatus.CREATED)
                                    .amount(totalAmount)
                                    .orderId(paymentRequest.getOrderId())
                                    .build());
        orders.setPaidOrder(true);
        orderClient.updateOrder(orders.getId(),orders.getPaidOrder());
        savedPayment.setOrder(orders);
        return paymentMapper.mapToPaymentResponse(savedPayment);
    }

    public PaymentResponse updatePaymentStatus(Integer paymentId, PaymentStatus status){
        Payment payment=findPaymentEntityById(paymentId);
        payment.setStatus(status);
        Payment updatePaymentByStatus=paymentRepository.save(payment);
        Orders orders=orderClient.findOrderWithProductById(updatePaymentByStatus.getOrderId());
        updatePaymentByStatus.setOrder(orders);
        return paymentMapper.mapToPaymentResponse(updatePaymentByStatus);
    }
    
}
