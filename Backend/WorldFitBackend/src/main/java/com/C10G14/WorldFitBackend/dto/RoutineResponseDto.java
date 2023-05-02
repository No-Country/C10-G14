package com.C10G14.WorldFitBackend.dto;


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
