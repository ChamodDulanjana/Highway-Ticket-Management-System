package com.chamoddulanjana.ticketservice.service;

import com.chamoddulanjana.ticketservice.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    void generateTicket(TicketDTO ticketDTO);
    void updateTicket(TicketDTO ticketDTO, String ticketNumber);
    TicketDTO getTicketByTicketNumber(String ticketNumber);
    List<TicketDTO> getAllTickets();
}
