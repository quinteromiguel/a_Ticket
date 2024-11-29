package com.project.aTicket.dto;

import com.project.aTicket.model.Seating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatingEventDTO {
    private Long id;
    private String type;
    private Double price;

    public static SeatingEventDTO fromEntity(Seating seatingEvent) {
        SeatingEventDTO seatingEventDTO = new SeatingEventDTO();
        seatingEventDTO.setId(seatingEvent.getId());
        seatingEventDTO.setType(seatingEvent.getType());
        seatingEventDTO.setPrice(seatingEvent.getPrice());
        return seatingEventDTO;
    }
}
