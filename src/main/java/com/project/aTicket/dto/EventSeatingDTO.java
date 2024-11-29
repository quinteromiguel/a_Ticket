package com.project.aTicket.dto;

import com.project.aTicket.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventSeatingDTO {
    private Long id;
    private String title;
    private String description;
    private String date;
    List<SeatingEventDTO> seating;

    public static EventSeatingDTO fromEntity(Event event) {
        EventSeatingDTO eventSeatingDTO = new EventSeatingDTO();
        eventSeatingDTO.setId(event.getId());
        eventSeatingDTO.setTitle(event.getTitle());
        eventSeatingDTO.setDescription(event.getDescription());
        eventSeatingDTO.setDate(event.getDate());
        eventSeatingDTO.setSeating(event.getSeating().stream().map(SeatingEventDTO::fromEntity).toList());
        return eventSeatingDTO;
    }


}
