package com.chamoddulanjana.ticketservice.util;

import com.chamoddulanjana.ticketservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerateTicketNumber {

    private final TicketRepository ticketRepository;

    public String generateNextTicketNumber(){
        String ticketNumber = ticketRepository.getTicketByLastTicketNumber();
        int nextNumber = 1;  // Default starting number if no tickets exist

        if (ticketNumber != null && ticketNumber.startsWith("T")) {
            String numericPart = ticketNumber.substring(3);
            nextNumber = Integer.parseInt(numericPart) + 1;
        }

        return "T" + String.format("%0" + 4 + "d", nextNumber);
    }
}
