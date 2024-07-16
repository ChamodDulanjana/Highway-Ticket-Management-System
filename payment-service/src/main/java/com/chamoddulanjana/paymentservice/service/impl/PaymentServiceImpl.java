package com.chamoddulanjana.paymentservice.service.impl;

import com.chamoddulanjana.paymentservice.dto.PaymentDTO;
import com.chamoddulanjana.paymentservice.dto.TicketDTO;
import com.chamoddulanjana.paymentservice.entity.Payment;
import com.chamoddulanjana.paymentservice.entity.enums.PaymentStatus;
import com.chamoddulanjana.paymentservice.exceptions.customExceptions.AlreadyExistException;
import com.chamoddulanjana.paymentservice.exceptions.customExceptions.NotFoundException;
import com.chamoddulanjana.paymentservice.repository.PaymentRepository;
import com.chamoddulanjana.paymentservice.service.PaymentService;
import com.chamoddulanjana.paymentservice.util.GenerateId;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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
            LOGGER.info("Ticket number {} not found", paymentDTO.getTicketNumber());
            throw new NotFoundException("Ticket not found");
        }

        if (ticketDTO.getPaymentStatus().toString().equalsIgnoreCase("PAID")){
            LOGGER.info("Ticket number {} already paid", paymentDTO.getTicketNumber());
            throw new AlreadyExistException("This ticket is already paid");
        }else {

            String id = GenerateId.getId("PAY").toLowerCase();

            Payment payment = Payment.
                    builder()
                    .id(id)
                    .dateTime(LocalDateTime.now())
                    .amount(paymentDTO.getAmount())
                    .paymentMethod(paymentDTO.getPaymentMethod())
                    .ticketNumber(paymentDTO.getTicketNumber())
                    .build();
            paymentRepository.save(payment);

            ticketDTO.setPaymentStatus(PaymentStatus.PAID);
            restTemplate.put("http://localhost:8080/api/v1/ticket/" + paymentDTO.getTicketNumber(), ticketDTO);
            LOGGER.info("Ticket number {} paid successfully", paymentDTO.getTicketNumber());
        }
    }

    @Override
    public PaymentDTO getPaymentByPaymentId(String paymentId) {
        LOGGER.info("Getting payment by payment id {}", paymentId);
        return paymentRepository.findById(paymentId).map(payment -> PaymentDTO.
                builder()
                .id(payment.getId())
                .dateTime(payment.getDateTime())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .ticketNumber(payment.getTicketNumber())
                .build()
        ).orElseThrow(() -> new NotFoundException("Payment not found"));
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream().map(payment -> PaymentDTO.
                builder()
                .id(payment.getId())
                .dateTime(payment.getDateTime())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .ticketNumber(payment.getTicketNumber())
                .build()
        ).toList();
    }
}
