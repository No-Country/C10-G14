package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.entity.Unit;
import com.C10G14.WorldFitBackend.enumeration.EUnit;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.function.Function;

@Service
public class ExerciseDtoMaper{

    public ExerciseDto EntityToDto (Exercise exercise) {
        return new ExerciseDto(
                exercise.getId(),
                exercise.getTitle(),
                exercise.getMedia(),
                exercise.getUnit().getName().toString()
        );
    }

    public Exercise DtoToEntity (ExerciseDto exerciseDto) {
        return new Exercise(
                exerciseDto.getTitle(),
                exerciseDto.getMedia(),
                new Unit(EUnit.valueOf(exerciseDto.getUnit()))// to do: cambiar implementacion
        );
    }

    }


