package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineDto;

import java.util.List;

public interface RoutineService {

    List<RoutineDto> getAllRoutines();

    RoutineDto getRoutineById (long id);

    RoutineDto createRoutine (RoutineDto routineDto);

    RoutineDto updateRoutine (long id, RoutineDto routineDto);
    void deleteRoutine (long id);
    RoutineDto addExercise(long routineId, Exercise_RoutineRequestDto exerciseId);

    RoutineDto updateExercise(long routineId, Exercise_RoutineRequestDto exercise);

    RoutineDto removeExercise(long routineId, long exerciseId);
}
