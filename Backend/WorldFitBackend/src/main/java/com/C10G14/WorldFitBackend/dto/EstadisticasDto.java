package com.C10G14.WorldFitBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticasDto {

    private Long id;
    private String fecha;
    private double totalIngresos;
    private double totalEgresos;

    //otros campos estadísticos
}
