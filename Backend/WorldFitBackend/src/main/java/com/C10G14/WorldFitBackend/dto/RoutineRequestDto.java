package com.C10G14.WorldFitBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RoutineRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

}
