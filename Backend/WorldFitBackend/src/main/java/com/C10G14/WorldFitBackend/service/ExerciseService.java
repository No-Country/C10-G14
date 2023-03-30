package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;

import java.util.List;

public interface ExerciseService {
    List<ExerciseDto> getAllExercises();

    ExerciseDto getExerciseById (Long id);

    ExerciseDto createExercise (ExerciseDto exerciseDto);

    ExerciseDto updateExercise (Long id, ExerciseDto exerciseDto);
    void deleteExercise (Long id);
}
