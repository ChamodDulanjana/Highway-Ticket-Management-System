package com.chamoddulanjana.ticketservice.repository;

import com.chamoddulanjana.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    Optional<Ticket> findTicketByTicketNumber(String ticketNumber);

    @Query(value = "SELECT ticket_Number FROM Ticket ORDER BY ticket_Number DESC LIMIT 1", nativeQuery = true)
    String getTicketByLastTicketNumber();

    Optional<Ticket> findTicketById(String ticketId);
}
