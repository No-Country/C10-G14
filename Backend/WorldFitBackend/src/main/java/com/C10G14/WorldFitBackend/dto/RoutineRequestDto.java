package com.C10G14.WorldFitBackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RoutineRequestDto {
    private long id;
    @NotNull(message = "userId cannot not be null")
    private long userId;
    @NotBlank(message = "Title can't be empty")
    private String title;
    private Set<Exercise_RoutineDto> exercises;

}
