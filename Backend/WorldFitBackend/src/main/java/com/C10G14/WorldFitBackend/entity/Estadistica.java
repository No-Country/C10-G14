package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Estadisticas")
public class Estadistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "total_ingresos")
    private double totalIngresos;

    @Column(name = "total_egresos")
    private double totalEgresos;

    //otros campos estadísticos

    public Estadistica() {
    }

    public Estadistica(LocalDate fecha, double totalIngresos, double totalEgresos) {
        this.fecha = fecha;
        this.totalIngresos = totalIngresos;
        this.totalEgresos = totalEgresos;
    }

    // getters y setters

}

