package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Exercise_Routine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RoutineDto {
    private long id;
    private String title;
    private Set<Exercise_RoutineDto> exercises;

}
