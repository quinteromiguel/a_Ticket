package com.project.aTicket.service;

import com.project.aTicket.dto.NewEventDTO;
import com.project.aTicket.dto.ReporteDTO;
import com.project.aTicket.model.Event;
import com.project.aTicket.model.Seating;
import com.project.aTicket.model.Ticket;
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

    public ReporteDTO getReporte(Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        Seating vip = event.getSeating().stream().filter(seating -> seating.getType().equals("VIP")).findFirst().orElseThrow();
        Seating general = event.getSeating().stream().filter(seating -> seating.getType().equals("General")).findFirst().orElseThrow();
        Integer totalGeneral = general.getTickets().stream().mapToInt(Ticket::getQuantity).sum();
        Integer totalVip = vip.getTickets().stream().mapToInt(Ticket::getQuantity).sum();

        return ReporteDTO.builder()
                .title(event.getTitle())
                .date(event.getDate())
                .description(event.getDescription())
                .vipPrice(vip.getPrice())
                .generalPrice(general.getPrice())
                .vipSold(totalVip)
                .generalSold(totalGeneral)
                .vipTotal(totalVip * vip.getPrice())
                .generalTotal(totalGeneral * general.getPrice())
                .total((totalVip * vip.getPrice()) + (totalGeneral * general.getPrice()))
                .build();
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
