package com.project.aTicket.service;

import com.project.aTicket.model.Profile;
import com.project.aTicket.model.Ticket;
import com.project.aTicket.model.Seating;
import com.project.aTicket.repository.ProfileRepository;

import com.project.aTicket.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ProfileRepository profileRepository;
    private final EventService eventService;


    public Optional<Ticket> createTicket(String document, Long seatingId) {
        Seating seating = eventService.getSeating(seatingId);
        if (seating.getCapacity() < 1) {
            return Optional.empty();
        }
        seating.setCapacity(seating.getCapacity() - 1);
        eventService.updateSeating(seating);
        Ticket ticket = ticketRepository.findByProfileDocumentAndSeating(document, seating)
                .orElse(newTicket(document, seating));
        return Optional.of(ticketRepository.save(ticket.setQuantity(ticket.getQuantity() + 1)));
    }

    private Ticket newTicket(String document, Seating seating) {
        Profile profile = profileRepository.findByDocument(document).orElseGet(() -> createProfile(document));
        return Ticket.builder()
                .profile(profile)
                .seating(seating)
                .quantity(0)
                .build();
    }

    private List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByDocument(String document) {
        return ticketRepository.findByProfileDocument(document);
    }

    public Profile createProfile(String document) {
        Profile profile = new Profile();
        profile.setDocument(document);
        return profileRepository.save(profile);
    }
}
