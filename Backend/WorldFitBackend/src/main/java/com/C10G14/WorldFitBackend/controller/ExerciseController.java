package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("")
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        List<ExerciseDto> exercises = exerciseService.getAllExercises();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable Long id) {
        ExerciseDto exercise = exerciseService.getExerciseById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    /*
    Body Ej:
    {
    "title" : "Press Banca",
    "description" : "descripcion del ejercicio",
    "media" : "url imagen",
    "unit" : "kg"
    }
    */
    @PostMapping("")
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exercise) {
        ExerciseDto createdExercise = exerciseService.createExercise(exercise);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable Long id, @RequestBody ExerciseDto exercise) {
        ExerciseDto updatedExercise = exerciseService.updateExercise(id, exercise);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
