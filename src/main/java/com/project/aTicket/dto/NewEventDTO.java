package com.project.aTicket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDTO {
    private String title;
    private String description;
    private String date;
    private Double vipPrice;
    private Double regularPrice;
}
