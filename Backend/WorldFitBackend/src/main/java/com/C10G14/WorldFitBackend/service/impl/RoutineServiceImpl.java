package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.RoutineDto;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.mapper.RoutineDtoMaper;
import com.C10G14.WorldFitBackend.repository.RoutineRepository;
import com.C10G14.WorldFitBackend.service.RoutineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineServiceImpl implements RoutineService {

    private RoutineRepository routineRepository;
    private RoutineDtoMaper routineDtoMaper;

    public RoutineServiceImpl(RoutineRepository routineRepository, RoutineDtoMaper routineDtoMaper) {
        this.routineRepository = routineRepository;
        this.routineDtoMaper = routineDtoMaper;
    }

    @Override
    public List<RoutineDto> getAllRoutines() {
        return null;
    }

    @Override
    public RoutineDto getRoutineById(Long id) {
        return null;
    }

    @Override
    public RoutineDto createRoutine(RoutineDto routineDto) {
        Routine routine = routineDtoMaper.DtoToEntity(routineDto);
        Routine newRoutine = routineRepository.save(routine);
        return routineDtoMaper.EntityToDto(newRoutine);
    }

    @Override
    public RoutineDto updateRoutine(Long id, RoutineDto routineDto) {
        return null;
    }

    @Override
    public void deleteRoutine(Long id) {

    }
}
