package com.project.aTicket.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {
    private String title;
    private String description;
    private String date;
    private Double vipPrice;
    private Integer vipSold;
    private Double generalPrice;
    private Integer generalSold;
    private Double vipTotal;
    private Double generalTotal;
    private Double total;
}
