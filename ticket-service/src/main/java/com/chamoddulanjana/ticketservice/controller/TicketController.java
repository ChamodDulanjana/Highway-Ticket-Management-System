package com.chamoddulanjana.ticketservice.controller;

import com.chamoddulanjana.ticketservice.dto.TicketDTO;
import com.chamoddulanjana.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    @GetMapping
    public String healthCheck(){
        return "Ticket Health Check";
    }

    @PostMapping
    public void generateTicket(@RequestBody TicketDTO ticketDTO){
        LOGGER.info("Generating ticket: {}", ticketDTO);
        ticketService.generateTicket(ticketDTO);
    }

    @GetMapping("/{ticketNumber}")
    public TicketDTO findTicketByTicketNumber(@PathVariable String ticketNumber){
        LOGGER.info("Finding ticket by number: {}", ticketNumber);
        return ticketService.getTicketByTicketNumber(ticketNumber);
    }

    @GetMapping("/getAll")
    public List<TicketDTO> findAllTickets(){
        LOGGER.info("Finding all tickets");
        return ticketService.getAllTickets();
    }

    @PutMapping
    public void updateTicket(@RequestBody TicketDTO ticketDTO, @RequestParam String ticketNumber){
        LOGGER.info("Updating ticket: {}", ticketDTO.getTicketNumber());
        ticketService.updateTicket(ticketDTO, ticketNumber);
    }
}
