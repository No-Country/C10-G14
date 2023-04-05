package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineDto;
import com.C10G14.WorldFitBackend.dto.RoutineDto;
import com.C10G14.WorldFitBackend.entity.Routine;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoutineDtoMapper {

    public RoutineDto EntityToDto (Routine routine) {
        // Routine tiene una lista de Exercise_Routine
        // RoutineDto tiene una lista de Exercise_RoutineDTO

        // Primero mapeo de Exercise_Routine a Exercise_RoutineDto
        Set<Exercise_RoutineDto> exercises = routine.getExercises().stream().map(e ->
        {return new Exercise_RoutineDto(
                e.getExercise(),e.getQuantity(),e.getSeries(),e.getRepetitions());})
                .collect(Collectors.toSet());

        // Luego mapeo el resto de las propiedades
        return new RoutineDto(
                routine.getId(),
                routine.getTitle(),
                exercises
        );
    }

    public Routine DtoToEntity (RoutineDto routineDto) {
        return new Routine(routineDto.getTitle());
    }

}
