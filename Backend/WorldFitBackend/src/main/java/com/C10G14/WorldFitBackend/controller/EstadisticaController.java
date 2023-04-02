package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.EstadisticaDto;
import com.C10G14.WorldFitBackend.entity.Estadistica;
import com.C10G14.WorldFitBackend.service.EstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticaController {

    @Autowired
    private EstadisticaService estadisticaService;

    @PostMapping
    public Estadistica guardarEstadistica(@RequestBody EstadisticaDto estadisticaDto) {
        Estadistica estadistica = new Estadistica();
        // mapear datos de estadisticasDto a estadistica
        estadistica.setFecha(LocalDate.parse(estadisticaDto.getFecha()));
        estadistica.setTotalIngresos(estadisticaDto.getTotalIngresos());
        estadistica.setTotalEgresos(estadisticaDto.getTotalEgresos());
        // ...
        return estadisticaService.guardar(estadistica);
    }



    @GetMapping
    public List<Estadistica> buscarTodasLasEstadisticas() {
        return estadisticaService.buscarTodas();
    }

    @GetMapping("/{id}")
    public Estadistica buscarEstadisticaPorId(@PathVariable Long id) {
        return estadisticaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstadisticaPorId(@PathVariable Long id) {
        estadisticaService.eliminarPorId(id);
    }
}
