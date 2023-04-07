package com.C10G14.WorldFitBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercise_RoutineRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Long exerciseId;
    private int quantity;
    private int repetitions;
    private int series;
}
