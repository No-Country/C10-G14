package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.exception.SqlConstraintException;
import com.C10G14.WorldFitBackend.mapper.ExerciseDtoMapper;
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
    private ExerciseDtoMapper DtoMaper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, UnitRepository unitRepository, ExerciseDtoMapper dtoMaper) {
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
        if(exerciseRepository.existsByTitle(exerciseDto.getTitle())){
            throw new AlreadyExistException("Error: An exercise with that title already exists: " + exerciseDto.getTitle());
        }
        Exercise newExercise = exerciseRepository.save(exercise);
        return DtoMaper.EntityToDto(newExercise);
    }

    @Override
    public ExerciseDto updateExercise(Long id, ExerciseDto exerciseDto) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new NotFoundException("Exercise not found"));
        Exercise updatedExercise = DtoMaper.DtoToEntity(exerciseDto);
        updatedExercise.setId(exercise.getId());
        try {
            exerciseRepository.save(updatedExercise);
            return DtoMaper.EntityToDto(updatedExercise);
        }catch (Exception e){
            throw new AlreadyExistException("An exercise with that title already exists: " + exerciseDto.getTitle());
        }
    }

    @Override
    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()-> new NotFoundException("Exercise not found"));
        try {
            exerciseRepository.delete(exercise);
        }catch(Exception e){
            throw new SqlConstraintException("Integrity issue: the exercise is contained by routines");
        }
    }
}
