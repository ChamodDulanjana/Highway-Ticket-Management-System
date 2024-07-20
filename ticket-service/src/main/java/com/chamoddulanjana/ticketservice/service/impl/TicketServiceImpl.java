package com.chamoddulanjana.ticketservice.service.impl;

import com.chamoddulanjana.ticketservice.dto.TicketDTO;
import com.chamoddulanjana.ticketservice.dto.VehicleDTO;
import com.chamoddulanjana.ticketservice.entity.Ticket;
import com.chamoddulanjana.ticketservice.exceptions.customExceptions.NotFoundException;
import com.chamoddulanjana.ticketservice.repository.TicketRepository;
import com.chamoddulanjana.ticketservice.service.TicketService;
import com.chamoddulanjana.ticketservice.util.GenerateId;
import com.chamoddulanjana.ticketservice.util.GenerateTicketNumber;
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
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final RestTemplate restTemplate;
    private final GenerateTicketNumber generateTicketNumber;
    private final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public void generateTicket(TicketDTO ticketDTO) {

        try {
            restTemplate.getForObject("http://localhost:8080/api/v1/vehicle/id/" + ticketDTO.getVehicleId(), VehicleDTO.class, VehicleDTO.class);
        }catch (Exception exception){
            LOGGER.error(exception.getMessage());
            throw new NotFoundException("Vehicle id " + ticketDTO.getVehicleId() + " not found");
        }

        String id = GenerateId.getId("TIC").toLowerCase();
        String ticketNumber = generateTicketNumber.generateNextTicketNumber();

        Ticket ticket = Ticket.builder()
                .id(id)
                .ticketNumber(ticketNumber)
                .entranceTerminal(ticketDTO.getEntranceTerminal())
                .exitTerminal(ticketDTO.getExitTerminal())
                .date(LocalDateTime.now())
                .amount(ticketDTO.getAmount())
                .paymentStatus(ticketDTO.getPaymentStatus())
                .vehicleId(ticketDTO.getVehicleId())
                .build();

        ticketRepository.save(ticket);
        LOGGER.info("Ticket generated successfully:{}", ticket.getTicketNumber());
    }

    @Override
    public void updateTicket(TicketDTO ticketDTO, String ticketNumber) {

        try {
            restTemplate.getForObject("http://localhost:8080/api/v1/vehicle/id/" + ticketDTO.getVehicleId(), VehicleDTO.class, VehicleDTO.class);
        }catch (Exception exception){
            LOGGER.error(exception.getMessage());
            throw new NotFoundException("Vehicle id " + ticketDTO.getVehicleId() + " not found");
        }

        Ticket ticket = ticketRepository.findTicketByTicketNumber(ticketNumber.toLowerCase()).orElseThrow(() -> new NotFoundException("Ticket not found"));
        ticket.setId(ticketDTO.getId());
        ticket.setEntranceTerminal(ticketDTO.getEntranceTerminal());
        ticket.setExitTerminal(ticketDTO.getExitTerminal());
        ticket.setAmount(ticketDTO.getAmount());
        ticket.setPaymentStatus(ticketDTO.getPaymentStatus());
        ticketRepository.save(ticket);
        LOGGER.info("Ticket updated successfully:{}", ticket.getTicketNumber());
    }

    @Override
    public TicketDTO getTicketByTicketNumber(String ticketNumber) {
        LOGGER.info("Get ticket by ticket number:{}", ticketNumber);
        return ticketRepository.findTicketByTicketNumber(ticketNumber).map(ticket -> TicketDTO.
                builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .entranceTerminal(ticket.getEntranceTerminal())
                .exitTerminal(ticket.getExitTerminal())
                .amount(ticket.getAmount())
                .paymentStatus(ticket.getPaymentStatus())
                .vehicleId(ticket.getVehicleId())
                .build()
        ).orElseThrow(() -> new NotFoundException("Ticket not found"));
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        LOGGER.info("Get all tickets");
        return ticketRepository.findAll().stream().map(ticket -> TicketDTO.
                builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .entranceTerminal(ticket.getEntranceTerminal())
                .exitTerminal(ticket.getExitTerminal())
                .amount(ticket.getAmount())
                .paymentStatus(ticket.getPaymentStatus())
                .vehicleId(ticket.getVehicleId())
                .build()
        ).toList();
    }
}
