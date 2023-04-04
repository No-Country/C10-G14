package com.C10G14.WorldFitBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercise_RoutineRequestDto {
    private Long exerciseId;
    private int quantity;
    private int repetitions;
    private int series;
}
