package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.ExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.ExerciseResponseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.exception.SqlConstraintException;
import com.C10G14.WorldFitBackend.mapper.ExerciseDtoMapper;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.repository.UnitRepository;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseDtoMapper dtoMapper;

    @Override
    public List<ExerciseResponseDto> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(dtoMapper::EntityToDto).toList();
    }

    @Override
    public ExerciseResponseDto getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new NotFoundException("Exercise not found"));
        return dtoMapper.EntityToDto(exercise);
    }

    @Override
    public ExerciseResponseDto createExercise(ExerciseRequestDto exerciseDto) {
        if(exerciseRepository.existsByTitle(exerciseDto.getTitle())){
            throw new AlreadyExistException("Error: An exercise with that title already exists: " + exerciseDto.getTitle());
        }
        Exercise exercise = dtoMapper.DtoToEntity(exerciseDto);
        Exercise newExercise = exerciseRepository.save(exercise);
        return dtoMapper.EntityToDto(newExercise);
    }

    @Override
    public ExerciseResponseDto updateExercise(Long id, ExerciseRequestDto exerciseDto) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Exercise not found"));
        Exercise updatedExercise = dtoMapper.DtoToEntity(exerciseDto);
        updatedExercise.setId(exercise.getId());
        try {
            exerciseRepository.save(updatedExercise);
            return dtoMapper.EntityToDto(updatedExercise);
        }catch (Exception e){
            throw new AlreadyExistException("An exercise with that title already exists: " + exerciseDto.getTitle());
        }
    }

    @Override
    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new NotFoundException("Exercise not found"));
        try {
            exerciseRepository.delete(exercise);
        }catch(Exception e){
            throw new SqlConstraintException("Integrity issue: the exercise is contained by routines");
        }
    }
}
