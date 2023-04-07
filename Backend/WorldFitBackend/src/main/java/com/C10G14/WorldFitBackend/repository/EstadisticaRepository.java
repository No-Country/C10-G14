package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Estadistica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EstadisticaRepository extends JpaRepository<Estadistica, Long> {

    List<Estadistica> findAllByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}

