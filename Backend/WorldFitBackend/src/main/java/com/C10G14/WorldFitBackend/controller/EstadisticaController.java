package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.EstadisticaDto;
import com.C10G14.WorldFitBackend.entity.Estadistica;
import com.C10G14.WorldFitBackend.service.EstadisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticaController {

    @Autowired
    private EstadisticaService estadisticaService;

    @Operation(summary = "Create new statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new statistic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstadisticaService.class)) })})
    @PreAuthorize("hasRole('ADMIN')")
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

    @Operation(summary = "Get all statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of statistics",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstadisticaService.class)) })})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Estadistica> buscarTodasLasEstadisticas() {
        return estadisticaService.buscarTodas();
    }

    @Operation(summary = "Get a statistic by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A statistic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstadisticaService.class)) })})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Estadistica buscarEstadisticaPorId(@PathVariable Long id) {
        return estadisticaService.buscarPorId(id);
    }

    @Operation(summary = "Delete a statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EstadisticaService.class)) })})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarEstadisticaPorId(@PathVariable Long id) {
        estadisticaService.eliminarPorId(id);
    }
}
