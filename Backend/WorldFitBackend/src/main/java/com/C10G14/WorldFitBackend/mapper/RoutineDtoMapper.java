package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineDto;
import com.C10G14.WorldFitBackend.dto.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoutineDtoMapper {

    UserRepository userRepository;

    @Autowired
    public RoutineDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RoutineResponseDto EntityToDto (Routine routine) {
        // Routine tiene una lista de Exercise_Routine
        // RoutineDto tiene una lista de Exercise_RoutineDTO

        // Primero mapeo de Exercise_Routine a Exercise_RoutineDto
        Set<Exercise_RoutineDto> exercises = routine.getExercises().stream().map(e ->
        {return new Exercise_RoutineDto(
                e.getExercise(),e.getQuantity(),e.getSeries(),e.getRepetitions());})
                .collect(Collectors.toSet());

        // Luego mapeo el resto de las propiedades
        return new RoutineResponseDto(
                routine.getId(),
                routine.getTitle(),
                exercises
        );
    }

    public Routine DtoToEntity (RoutineRequestDto routineDto) {
        User user = userRepository.findById(routineDto.getUserId()).orElseThrow(()-> new NotFoundException("User not found"));
        return new Routine(routineDto.getTitle(),user);
    }

}
