package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.mapper.RoutineDtoMaper;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.repository.RoutineRepository;
import com.C10G14.WorldFitBackend.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineServiceImpl implements RoutineService {

    private RoutineRepository routineRepository;
    private ExerciseRepository exerciseRepository;
    private RoutineDtoMaper DtoMaper;

    @Autowired
    public RoutineServiceImpl(RoutineRepository routineRepository, ExerciseRepository exerciseRepository, RoutineDtoMaper dtoMaper) {
        this.routineRepository = routineRepository;
        this.exerciseRepository = exerciseRepository;
        this.DtoMaper = dtoMaper;
    }

    @Override
    public List<RoutineDto> getAllRoutines() {
        List<Routine> routines = routineRepository.findAll();
        return routines.stream().map(e -> DtoMaper.EntityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public RoutineDto getRoutineById(long id) {
        return null;
    }

    @Override
    public RoutineDto createRoutine(RoutineDto routineDto) {
        Routine routine = DtoMaper.DtoToEntity(routineDto);
        Routine newRoutine = routineRepository.save(routine);
        return DtoMaper.EntityToDto(newRoutine);
    }

    @Override
    public RoutineDto updateRoutine(long id, RoutineDto routineDto) {
        return null;
    }

    @Override
    public void deleteRoutine(long id) {

    }

    public RoutineDto addExercise(long routineId, Exercise_RoutineRequestDto e) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()-> new RuntimeException());
        Exercise exercise = exerciseRepository.findById(e.getExerciseId()).orElseThrow(()-> new ArithmeticException());

        routine.addExercise(exercise,e.getQuantity(),e.getSeries(),e.getRepetitions());
        Routine updatedRoutine = routineRepository.save(routine);

        return DtoMaper.EntityToDto(updatedRoutine);
    }

    @Override
    public RoutineDto updateExercise(long routineId, Exercise_RoutineRequestDto e) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()-> new RuntimeException());
        Exercise exercise = exerciseRepository.findById(e.getExerciseId()).orElseThrow(()-> new RuntimeException());

        routine.updateExercise(exercise,e.getQuantity(),e.getSeries(),e.getRepetitions());
        Routine updatedRoutine = routineRepository.save(routine);

        return DtoMaper.EntityToDto(updatedRoutine);
    }

    @Override
    public RoutineDto removeExercise(long routineId, long exerciseId) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()-> new RuntimeException());
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(()-> new RuntimeException());

        routine.removeExercise(exercise);
        Routine updatedRoutine = routineRepository.save(routine);

        System.out.println(updatedRoutine);

        return DtoMaper.EntityToDto(updatedRoutine);
    }
}
