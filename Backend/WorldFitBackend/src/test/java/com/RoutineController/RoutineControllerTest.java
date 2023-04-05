package com.RoutineController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.C10G14.WorldFitBackend.controller.RoutineController;
import com.C10G14.WorldFitBackend.dto.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineDto;
import com.C10G14.WorldFitBackend.service.RoutineService;


@RunWith(MockitoJUnitRunner.class)
public class RoutineControllerTest {

    @Mock
    private RoutineService routineService;

    @InjectMocks
    private RoutineController routineController;

    @Test
    public void testAddExercise() {
        // Preparación de datos
        Exercise_RoutineRequestDto exercise = new Exercise_RoutineRequestDto(2L,2,3,42);
        exercise.setExerciseId(1L);
        exercise.setQuantity(10);
        exercise.setSeries(5);
        exercise.setRepetitions(5);

        RoutineDto routine = new RoutineDto(1,"ejercicio", "ejercicio2");
        routine.setId(1L);

        when(routineService.addExercise(1L, exercise)).thenReturn(routine);

        // Ejecución del método a probar
        ResponseEntity<RoutineDto> response = routineController.addExercise(1L, exercise);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routine, response.getBody());
    }
    @Test
    public void testGetAllRoutines() {
        // Preparación de datos
        List<RoutineDto> routines = new ArrayList<>();
        RoutineDto routine1 = new RoutineDto(1,"ejercicio", "ejercicio2");
        routine1.setId(1L);
        RoutineDto routine2 = new RoutineDto(1,"ejercicio", "ejercicio2");
        routine2.setId(2L);
        routines.add(routine1);
        routines.add(routine2);

        when(routineService.getAllRoutines()).thenReturn(routines);

        // Ejecución del método a probar
        ResponseEntity<List<RoutineDto>> response = routineController.getAllRoutines();

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routines, response.getBody());
    }
    @Test
    public void testGetRoutineById() {
        // Preparación de datos
        RoutineDto routine = new RoutineDto(1,"ejercicio", "ejercicio2");
        routine.setId(1L);

        when(routineService.getRoutineById(1L)).thenReturn(routine);

        // Ejecución del método a probar
        ResponseEntity<RoutineDto> response = routineController.getRoutineById(1L);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routine, response.getBody());
    }
    @Test
    public void testCreateRoutine() {
        // Preparación de datos
        RoutineDto routine = new RoutineDto(1,"ejercicio", "ejercicio2");
        routine.setId(1L);

        when(routineService.createRoutine(routine)).thenReturn(routine);

        // Ejecución del método a probar
        ResponseEntity<RoutineDto> response = routineController.createRoutine(routine);

        // Verificación del resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(routine, response.getBody());
    }

    @Test
    public void testUpdateRoutine() {
        // Preparación de datos
        RoutineDto routine = new RoutineDto(1,"ejercicio", "ejercicio2");
        routine.setId(1L);

        when(routineService.updateRoutine(1L, routine)).thenReturn(routine);

        // Ejecución del método a probar
        ResponseEntity<RoutineDto> response = routineController.updateRoutine(1L, routine);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routine, response.getBody());
    }
    @Test
    public void testDeleteRoutine() {
        // Ejecución del método a probar
        ResponseEntity<Void> response = routineController.deleteRoutine(1L);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateExercise() {
        // Preparación de datos
        long routineId = 1L;
        Exercise_RoutineRequestDto exercise = new Exercise_RoutineRequestDto(2L,2,3,42);
        exercise.setExerciseId(1L);
        exercise.setQuantity(10);
        exercise.setSeries(5);
        exercise.setRepetitions(5);

        RoutineDto routine = new RoutineDto(1,"ejercicio", "ejercicio2");
        routine.setId(1L);

        when(routineService.updateExercise(routineId, exercise)).thenReturn(routine);

        // Ejecución del método a probar
        ResponseEntity<RoutineDto> response = routineController.updateExercise(routineId, exercise);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routine, response.getBody());
    }


}






