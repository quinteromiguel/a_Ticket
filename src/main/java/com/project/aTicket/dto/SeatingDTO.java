package com.project.aTicket.dto;

import com.project.aTicket.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.project.aTicket.model.Seating;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatingDTO {
    private Long id;
    private String type;
    private Double price;
    private EventDTO event;

    public static SeatingDTO fromEntity(Seating seating) {
        SeatingDTO seatingDTO = new SeatingDTO();
        seatingDTO.setId(seating.getId());
        seatingDTO.setType(seating.getType());
        seatingDTO.setPrice(seating.getPrice());
        seatingDTO.setEvent(EventDTO.fromEntity(seating.getEvent()));
        return seatingDTO;
    }
}
