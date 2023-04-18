package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.ExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.ExerciseResponseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.exception.CantBeEmptyException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.repository.UnitRepository;
import com.C10G14.WorldFitBackend.util.DtoFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ExerciseDtoMapper {

     @Autowired
     UnitRepository unitRepository;
     @Autowired
     DtoFormatter formatter;

    public ExerciseResponseDto EntityToDto (Exercise exercise) {
        return new ExerciseResponseDto(
                exercise.getId(),
                exercise.getTitle(),
                exercise.getDescription(),
                exercise.getMedia(),
                exercise.getUnit().getName().toString()
        );
    }

    public Exercise DtoToEntity (ExerciseRequestDto exerciseDto) {

        String description = exerciseDto.getDescription();
        if (description == null || description.isEmpty()) {
            description = null;
        } else {
            description = description.substring(0, 1).toUpperCase() + description.substring(1).toLowerCase();
        }

        return new Exercise(
                formatter.format(exerciseDto.getTitle()),
                description,
                exerciseDto.getMedia(),
                unitRepository.findByName(exerciseDto.unitToEUnit())
                        .orElseThrow(() -> new NotFoundException("Unit must be either: 'Km', 'Kg', 'Minutos' or 'null'"))
        );
    }
}


