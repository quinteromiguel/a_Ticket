package com.project.aTicket.dto;

import com.project.aTicket.model.Event;
import com.project.aTicket.model.Profile;
import com.project.aTicket.model.Seating;
import com.project.aTicket.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private Integer quantity;
    private SeatingDTO seating;
    private Profile profile;

    public static TicketDTO fromEntity(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setQuantity(ticket.getQuantity());
        ticketDTO.setProfile(ticket.getProfile());
        ticketDTO.setSeating(SeatingDTO.fromEntity(ticket.getSeating()));
        return ticketDTO;
    }
}
