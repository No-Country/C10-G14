package com.C10G14.WorldFitBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercise_RoutineRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "excerseId is required")
    private Long exerciseId;
    private int quantity;
    private int repetitions;
    private int series;
}
