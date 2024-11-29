package com.project.aTicket.service;

import com.project.aTicket.dto.NewEventDTO;
import com.project.aTicket.model.Event;
import com.project.aTicket.model.Seating;
import com.project.aTicket.repository.EventRepository;
import com.project.aTicket.repository.SeatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final SeatingRepository seatingRepository;

    public Seating getSeating(Long id) {
        return seatingRepository.findById(id).orElseThrow();
    }

    public Event createEvent(NewEventDTO newEventDTO) {
        Event event = Event.builder()
                .title(newEventDTO.getTitle())
                .date(newEventDTO.getDate())
                .description(newEventDTO.getDescription())
                .build();
        Seating vip = Seating.builder()
                .type("VIP")
                .price(newEventDTO.getVipPrice())
                .capacity(50)
                .tickets(List.of())
                .event(event)
                .build();
        Seating general = Seating.builder()
                .type("General")
                .price(newEventDTO.getRegularPrice())
                .capacity(100)
                .tickets(List.of())
                .event(event)
                .build();
        event.setSeating(List.of(vip, general));
        return eventRepository.save(event);
    }
    public Seating updateSeating(Seating seating) {
        return seatingRepository.save(seating);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event updateEvent(Long id, String name, String date, String description) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.setTitle(name);
        event.setDate(date);
        event.setDescription(description);
        return eventRepository.save(event);
    }

}
