package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.ExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.ExerciseResponseDto;

import java.util.List;

public interface ExerciseService {
    List<ExerciseResponseDto> getAllExercises();

    ExerciseResponseDto getExerciseById (Long id);

    ExerciseResponseDto createExercise (ExerciseRequestDto exerciseDto);

    ExerciseResponseDto updateExercise (Long id, ExerciseRequestDto exerciseDto);
    void deleteExercise (Long id);
}
