package com.project.aTicket.controller;

import com.project.aTicket.dto.EventSeatingDTO;
import com.project.aTicket.dto.NewEventDTO;
import com.project.aTicket.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping()
    public ResponseEntity<List<EventSeatingDTO>> getAllEvents() {
        return ResponseEntity.ok(
                eventService.getEvents()
                        .stream()
                        .map(EventSeatingDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventSeatingDTO> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(
                EventSeatingDTO.fromEntity(
                        eventService.getEvent(id)
                )
        );
    }

    @PostMapping()
    public ResponseEntity<EventSeatingDTO> createEvent(@RequestBody NewEventDTO event) {
        return ResponseEntity.ok(
                EventSeatingDTO.fromEntity(
                eventService.createEvent(event)
                )
        );
    }

    @PutMapping()
    public ResponseEntity<EventSeatingDTO> updateEvent(@RequestParam Long id, @RequestParam String name, @RequestParam String date, @RequestParam String description) {
        return ResponseEntity.ok(
                EventSeatingDTO.fromEntity(
                eventService.updateEvent(id, name, date, description)
                )
        );
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteEvent(@RequestParam Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
