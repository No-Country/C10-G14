package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.RoutineDto;

import java.util.List;

public interface RoutineService {

    List<RoutineDto> getAllRoutines();

    RoutineDto getRoutineById (Long id);

    RoutineDto createRoutine (RoutineDto routineDto);

    RoutineDto updateRoutine (Long id, RoutineDto routineDto);
    void deleteRoutine (Long id);
}
