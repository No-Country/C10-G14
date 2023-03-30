package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.RoutineDto;
import com.C10G14.WorldFitBackend.repository.RoutineRepository;
import com.C10G14.WorldFitBackend.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineServiceImpl implements RoutineService {

    private RoutineRepository routineRepository;

    @Autowired
    public RoutineServiceImpl(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
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
        return null;
    }

    @Override
    public RoutineDto updateRoutine(Long id, RoutineDto routineDto) {
        return null;
    }

    @Override
    public void deleteRoutine(Long id) {

    }
}
