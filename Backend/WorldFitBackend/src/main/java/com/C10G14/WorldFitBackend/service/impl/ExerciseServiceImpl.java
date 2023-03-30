package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
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
        return null;
    }

    @Override
    public ExerciseDto updateExercise(Long id, ExerciseDto exerciseDto) {
        return null;
    }

    @Override
    public void deleteExercise(Long id) {

    }
}
