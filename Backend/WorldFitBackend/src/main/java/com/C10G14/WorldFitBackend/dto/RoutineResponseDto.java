package com.C10G14.WorldFitBackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutineResponseDto {
    private long id;
    private String title;
    private Set<Exercise_RoutineResponseDto> exercises;

}
