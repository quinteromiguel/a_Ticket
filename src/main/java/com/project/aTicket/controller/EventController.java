package com.project.aTicket.controller;

import com.project.aTicket.dto.NewEventDTO;
import com.project.aTicket.dto.ReporteDTO;
import com.project.aTicket.model.Event;
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
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(
                eventService.getEvents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @PostMapping()
    public ResponseEntity<Event> createEvent(@RequestBody NewEventDTO event) {
        return ResponseEntity.ok(eventService.createEvent(event)
        );
    }

    @PutMapping()
    public ResponseEntity<Event> updateEvent(@RequestParam Long id, @RequestParam String name, @RequestParam String date, @RequestParam String description) {
        return ResponseEntity.ok(eventService.updateEvent(id, name, date, description)

        );
    }

    @GetMapping("/reporte")
    public ResponseEntity<ReporteDTO> getReporte(@RequestParam Long id) {
        return ResponseEntity.ok(eventService.getReporte(id));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteEvent(@RequestParam Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
