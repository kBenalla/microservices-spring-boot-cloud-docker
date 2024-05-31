package org.com.microservicepayment.controller;

import lombok.AllArgsConstructor;
import org.com.microservicepayment.clients.MicroserviceOrderClient;
import org.com.microservicepayment.dtos.PaymentRequest;
import org.com.microservicepayment.dtos.PaymentResponse;
import org.com.microservicepayment.entity.Payment;
import org.com.microservicepayment.enums.PaymentStatus;
import org.com.microservicepayment.model.Orders;
import org.com.microservicepayment.repository.PaymentRepository;
import org.com.microservicepayment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping
    public List<PaymentResponse> findAllPaymentsWithOrder(){
        return paymentService.findAllPaymentsWithOrder();
    }

    @GetMapping("/{id}")
    public PaymentResponse findPaymentWithOrderById(@PathVariable Integer id){
        return paymentService.findPaymentWithOrderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse savePayment(@RequestBody PaymentRequest paymentRequest){
        return paymentService.savePayment(paymentRequest);
    }

    @PutMapping("/{id}/updateStatus")
    public PaymentResponse updatePaymentByStatus(@PathVariable Integer id,
                                                 @RequestHeader PaymentStatus status){
        return paymentService.updatePaymentStatus(id,status);
    }
}
