package com.chamoddulanjana.paymentservice.service;

import com.chamoddulanjana.paymentservice.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    void generatePayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentByPaymentId(String paymentId);
    List<PaymentDTO> getAllPayments();
}
