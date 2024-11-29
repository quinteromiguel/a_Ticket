package com.project.aTicket.repository;

import com.project.aTicket.model.Seating;
import com.project.aTicket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByProfileDocument(String document);

    Optional<Ticket> findByProfileDocumentAndSeating(String profileDocument, Seating seating);
}
