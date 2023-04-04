package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.mapper.ExerciseDtoMaper;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.repository.UnitRepository;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;
    private UnitRepository unitRepository;
    private ExerciseDtoMaper DtoMaper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, UnitRepository unitRepository, ExerciseDtoMaper dtoMaper) {
        this.exerciseRepository = exerciseRepository;
        this.unitRepository = unitRepository;
        DtoMaper = dtoMaper;
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(e -> DtoMaper.EntityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public ExerciseDto getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()-> new NotFoundException("Exercise not found"));
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
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new NotFoundException("Exercise not found"));
        exercise.setTitle(exerciseDto.getTitle());
        exercise.setDescription(exercise.getDescription());
        exercise.setMedia(exercise.getMedia());
        exercise.setUnit(unitRepository.findByName(exerciseDto.unitToEUnit()).orElseThrow(() -> new NotFoundException("Unit not found")));
        Exercise updatedExercise = exerciseRepository.save(exercise);
        return DtoMaper.EntityToDto(updatedExercise);
    }

    @Override
    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()-> new NotFoundException("Exercise not found"));
        exerciseRepository.delete(exercise);
    }
}
