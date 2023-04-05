package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.mapper.RoutineDtoMapper;
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
    private RoutineDtoMapper DtoMaper;

    @Autowired
    public RoutineServiceImpl(RoutineRepository routineRepository, ExerciseRepository exerciseRepository, RoutineDtoMapper dtoMaper) {
        this.routineRepository = routineRepository;
        this.exerciseRepository = exerciseRepository;
        this.DtoMaper = dtoMaper;
    }

    @Override
    public List<RoutineResponseDto> getAllRoutines() {
        List<Routine> routines = routineRepository.findAll();
        return routines.stream().map(e -> DtoMaper.EntityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public RoutineResponseDto getRoutineById(long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(()-> new NotFoundException("Routine not found"));
        return DtoMaper.EntityToDto(routine);
    }

    @Override
    public RoutineResponseDto createRoutine(RoutineRequestDto routineDto) {
        Routine routine = DtoMaper.DtoToEntity(routineDto);
        Routine newRoutine = routineRepository.save(routine);
        return DtoMaper.EntityToDto(newRoutine);
    }

    @Override
    public RoutineResponseDto updateRoutine(long id, RoutineRequestDto routineDto) {
        Routine routine = routineRepository.findById(id).orElseThrow(()-> new NotFoundException("Routine not found"));

        routine.setTitle(routineDto.getTitle());
        Routine updatedRoutine = routineRepository.save(routine);

        return DtoMaper.EntityToDto(updatedRoutine);
    }

    @Override
    public void deleteRoutine(long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(()-> new NotFoundException("Routine not found"));
        routineRepository.delete(routine);
    }

    public RoutineResponseDto addExercise(long routineId, Exercise_RoutineRequestDto e) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()-> new NotFoundException("Routine not found"));
        Exercise exercise = exerciseRepository.findById(e.getExerciseId()).orElseThrow(()-> new NotFoundException("Exercise not found"));

        routine.addExercise(exercise,e.getQuantity(),e.getSeries(),e.getRepetitions());
        Routine updatedRoutine = routineRepository.save(routine);

        return DtoMaper.EntityToDto(updatedRoutine);
    }

    @Override
    public RoutineResponseDto updateExercise(long routineId, Exercise_RoutineRequestDto e) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()-> new NotFoundException("Routine not found"));
        Exercise exercise = exerciseRepository.findById(e.getExerciseId()).orElseThrow(()-> new NotFoundException("Exercise not found"));

        routine.updateExercise(exercise,e.getQuantity(),e.getSeries(),e.getRepetitions());
        Routine updatedRoutine = routineRepository.save(routine);

        return DtoMaper.EntityToDto(updatedRoutine);
    }

    @Override
    public RoutineResponseDto removeExercise(long routineId, long exerciseId) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()-> new NotFoundException("Routine not found"));
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(()-> new NotFoundException("Exercise not found"));

        routineRepository.removeExercise(routineId,exerciseId);

        routine.removeExercise(exercise);
        Routine updatedRoutine = routineRepository.save(routine);

        return DtoMaper.EntityToDto(updatedRoutine);
    }

}
