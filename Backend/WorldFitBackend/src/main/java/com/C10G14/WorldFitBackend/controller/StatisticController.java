package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.StatisticDto;
import com.C10G14.WorldFitBackend.dto.statisticDto;
import com.C10G14.WorldFitBackend.entity.Statistic;
import com.C10G14.WorldFitBackend.entity.statistic;
import com.C10G14.WorldFitBackend.service.StatisticService;
import com.C10G14.WorldFitBackend.service.statisticService;
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
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @Operation(summary = "Create new statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new statistic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) })})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Statistic saveStatistic(@RequestBody StatisticDto statisticDto) {
        return statisticService.save();
    }

    @Operation(summary = "Get all statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of statistics",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) })})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Statistic> searchAllLasstatistics() {
        return statisticService.getAll();
    }

    @Operation(summary = "Get a statistic by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A statistic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) })})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Statistic searchtatisticById(@PathVariable Long id) {
        return statisticService.getById(id);
    }

    @Operation(summary = "Delete a statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) })})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteStatisticById(@PathVariable Long id) {
        statisticService.deleteById(id);
    }
}
