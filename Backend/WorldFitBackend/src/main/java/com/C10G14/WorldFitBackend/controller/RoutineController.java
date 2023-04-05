package com.C10G14.WorldFitBackend.controller;


import com.C10G14.WorldFitBackend.dto.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;
import com.C10G14.WorldFitBackend.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping
    public ResponseEntity<List<RoutineResponseDto>> getAllRoutines() {
        List<RoutineResponseDto> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponseDto> getRoutineById(@PathVariable Long id) {
        RoutineResponseDto routine = routineService.getRoutineById(id);
        return ResponseEntity.ok(routine);
    }

    /*
    Body ej:
    {
    "title" : "jueves",
    "userId" : 1
    }
     */
    @PostMapping
    public ResponseEntity<RoutineResponseDto> createRoutine(@RequestBody RoutineRequestDto routine) {
        RoutineResponseDto createdRoutine = routineService.createRoutine(routine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutineResponseDto> updateRoutine(@PathVariable Long id, @RequestBody RoutineRequestDto routine) {
        //routine.setId(id);
        RoutineResponseDto updatedRoutine = routineService.updateRoutine(id, routine);
        return ResponseEntity.ok(updatedRoutine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.ok().build();
    }

        /*
       Body Ej (POST, PUT):
       {
       "exerciseId" : 1,
       "quantity" : 10,
       "series" : 5,
       "repetitions" : 5
       }
        */
    @PostMapping("/{id}/exercises")
    public ResponseEntity<RoutineResponseDto> addExercise(@PathVariable("id") long routineId,
                                                  @RequestBody Exercise_RoutineRequestDto exercise) {
        RoutineResponseDto updatedRoutine = routineService.addExercise(routineId,exercise);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    @PutMapping("/{id}/exercises")
    public ResponseEntity<RoutineResponseDto> updateExercise(@PathVariable("id") long routineId,
                                                  @RequestBody Exercise_RoutineRequestDto exercise) {
        RoutineResponseDto updatedRoutine = routineService.updateExercise(routineId,exercise);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    /*
    Body Ej:
    {
    "exerciseId" : 1
    }
     */
    @DeleteMapping("/{id}/exercises")
    public ResponseEntity<RoutineResponseDto> removeExercise(@PathVariable("id") long routineId,
                                                  @RequestBody Map <String,Long> exerciseId) {
        RoutineResponseDto updatedRoutine = routineService.removeExercise(routineId,exerciseId.get("exerciseId"));
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }



}
