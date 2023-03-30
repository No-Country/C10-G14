package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.RoutineDto;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping
    public ResponseEntity<List<RoutineDto>> getAllRoutines() {
        List<RoutineDto> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineDto> getRoutineById(@PathVariable Long id) {
        RoutineDto routine = routineService.getRoutineById(id);
        return ResponseEntity.ok(routine);
    }

    @PostMapping
    public ResponseEntity<RoutineDto> createRoutine(@RequestBody RoutineDto routine) {
        RoutineDto createdRoutine = routineService.createRoutine(routine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutineDto> updateRoutine(@PathVariable Long id, @RequestBody RoutineDto routine) {
        //routine.setId(id);
        RoutineDto updatedRoutine = routineService.updateRoutine(id, routine);
        return ResponseEntity.ok(updatedRoutine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.ok().build();
    }}
