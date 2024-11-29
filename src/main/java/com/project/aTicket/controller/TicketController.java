package com.project.aTicket.controller;

import com.project.aTicket.dto.TicketDTO;
import com.project.aTicket.model.Ticket;
import com.project.aTicket.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    // Esto es un enpoint usando opcionales para el manejo de errores
    @PostMapping()
    public ResponseEntity<TicketDTO> createTicket(@RequestHeader String document, @RequestHeader Long seatingId) {
        return ticketService.createTicket(document, seatingId)
                .map(TicketDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping()
    public ResponseEntity<List<TicketDTO>> getTicketByDocument(@RequestHeader String document) {
        return ResponseEntity.ok(
                ticketService.getTicketsByDocument(document).stream()
                        .map(TicketDTO::fromEntity)
                        .toList()
        );
    }
}
