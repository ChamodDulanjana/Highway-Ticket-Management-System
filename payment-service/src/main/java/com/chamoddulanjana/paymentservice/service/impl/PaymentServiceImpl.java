package com.chamoddulanjana.paymentservice.service.impl;

import com.chamoddulanjana.paymentservice.dto.PaymentDTO;
import com.chamoddulanjana.paymentservice.dto.TicketDTO;
import com.chamoddulanjana.paymentservice.exceptions.customExceptions.NotFoundException;
import com.chamoddulanjana.paymentservice.repository.PaymentRepository;
import com.chamoddulanjana.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public void generatePayment(PaymentDTO paymentDTO) {
        TicketDTO ticketDTO = restTemplate.getForObject("http://localhost:8080/api/v1/ticket/" + paymentDTO.getTicketNumber(), TicketDTO.class);
        if (ticketDTO == null) {
            LOGGER.warn("Ticket number {} not found", paymentDTO.getTicketNumber());
            throw new NotFoundException("Ticket not found");
        }

    }

    @Override
    public PaymentDTO getPaymentByPaymentId(String paymentId) {
        return null;
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return List.of();
    }

    @Override
    public void updatePayment(PaymentDTO paymentDTO, String paymentId) {

    }
}
