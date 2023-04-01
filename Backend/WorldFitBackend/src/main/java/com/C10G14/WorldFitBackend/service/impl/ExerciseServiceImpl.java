package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.mapper.ExerciseDtoMaper;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;
    private ExerciseDtoMaper DtoMaper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseDtoMaper exerciseDtoMaper) {
        this.exerciseRepository = exerciseRepository;
        this.DtoMaper = exerciseDtoMaper;
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(e -> DtoMaper.EntityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public ExerciseDto getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return DtoMaper.EntityToDto(exercise);
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto exerciseDto) {
        Exercise exercise = DtoMaper.DtoToEntity(exerciseDto);
        Exercise newExercise = exerciseRepository.save(exercise);
        return DtoMaper.EntityToDto(newExercise);
    }

    @Override
    public ExerciseDto updateExercise(Long id, ExerciseDto exerciseDto) {
        return null;
    }

    @Override
    public void deleteExercise(Long id) {

    }
}
