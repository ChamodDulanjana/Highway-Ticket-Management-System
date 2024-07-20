package com.chamoddulanjana.ticketservice.service;

import com.chamoddulanjana.ticketservice.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    void generateTicket(TicketDTO ticketDTO);
    void updateTicket(TicketDTO ticketDTO, String id);
    TicketDTO getTicketByTicketNumber(String ticketNumber);
    List<TicketDTO> getAllTickets();
    TicketDTO getTicketByTicketId(String ticketId);
}
