package com.chamoddulanjana.paymentservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    @GetMapping("/health")
    public String healthCheck(){
        return "Payment Health Check";
    }
}
