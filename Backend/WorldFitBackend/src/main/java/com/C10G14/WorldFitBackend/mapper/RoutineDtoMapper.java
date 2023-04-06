package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineDto;
import com.C10G14.WorldFitBackend.dto.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.exception.CantBeEmptyException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.repository.RoutineRepository;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoutineDtoMapper {

    UserRepository userRepository;
    RoutineRepository routineRepository;

    @Autowired
    public RoutineDtoMapper(UserRepository userRepository, RoutineRepository repository) {
        this.userRepository = userRepository;
        this.routineRepository = repository;
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
        if (Objects.equals(routineDto.getTitle(),"")
                || routineDto.getTitle() == null){
            throw new CantBeEmptyException("Title is required");
        }
        if(routineRepository.existsByTitleAndUser_Id(routineDto.getTitle(), routineDto.getUserId())){
            throw new AlreadyExistException("The User already has routine with that name: " + routineDto.getTitle());
        };

        User user = userRepository.findById(routineDto.getUserId()).orElseThrow(()-> new NotFoundException("User not found"));
        return new Routine(routineDto.getTitle(),user);
    }

}
