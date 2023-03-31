package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.mapper.ExerciseDtoMaper;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;
    private ExerciseDtoMaper exerciseDtoMaper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseDtoMaper exerciseDtoMaper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseDtoMaper = exerciseDtoMaper;
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        return null;
    }

    @Override
    public ExerciseDto getExerciseById(Long id) {
        return null;
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto exerciseDto) {
        Exercise exercise = exerciseDtoMaper.DtoToEntity(exerciseDto);
        Exercise newExercise = exerciseRepository.save(exercise);
        return exerciseDtoMaper.EntityToDto(newExercise);
    }

    @Override
    public ExerciseDto updateExercise(Long id, ExerciseDto exerciseDto) {
        return null;
    }

    @Override
    public void deleteExercise(Long id) {

    }
}
