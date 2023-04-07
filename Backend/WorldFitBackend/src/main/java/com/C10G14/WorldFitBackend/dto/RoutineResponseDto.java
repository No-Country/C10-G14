package com.C10G14.WorldFitBackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RoutineResponseDto {
    private long id;
    private String title;
    private Set<Exercise_RoutineDto> exercises;

}
