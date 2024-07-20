package com.chamoddulanjana.paymentservice.controller;

import com.chamoddulanjana.paymentservice.dto.PaymentDTO;
import com.chamoddulanjana.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/health")
    public String healthCheck(){
        return "Payment Health Check";
    }

    @PostMapping
    public void generatePayment(@RequestBody PaymentDTO paymentDTO){
        LOGGER.info("Generating payment for {}", paymentDTO.getId());
        paymentService.generatePayment(paymentDTO);
    }

    @GetMapping("/{id}")
    public PaymentDTO getPaymentById(@PathVariable String id){
        LOGGER.info("Getting payment for {}", id);
        return paymentService.getPaymentByPaymentId(id);
    }

    @GetMapping("/getAll")
    public List<PaymentDTO> getAllPayments(){
        LOGGER.info("Getting payment list");
        return paymentService.getAllPayments();
    }
}
