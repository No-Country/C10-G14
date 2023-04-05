package com.RoutineController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.*;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineDto;
import com.C10G14.WorldFitBackend.dto.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.C10G14.WorldFitBackend.controller.RoutineController;

import com.C10G14.WorldFitBackend.service.RoutineService;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class RoutineControllerTest {

    @Mock
    private RoutineService routineService;

    @InjectMocks
    private RoutineController routineController;


    @Test
    public void testCreateRoutine() {
        // Preparación de datos
        Set<Exercise_RoutineDto> exercises = new HashSet<>();
        Exercise exercise = new Exercise();
        exercise.setId(1L);
        exercise.setName("Sentadillas");
        exercises.add(new Exercise_RoutineDto(exercise, 2, 2, 4));
        RoutineRequestDto routine = new RoutineRequestDto(1, 2, "safas", exercises);
        routine.setId(1L);

        when(routineService.createRoutine(routine)).thenReturn(new RoutineResponseDto(1, "safgsa", exercises));

        // Ejecución del método a probar
        ResponseEntity<RoutineResponseDto> response = routineController.createRoutine(routine);

        // Verificación del resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        RoutineResponseDto createdRoutine = response.getBody();
        assertNotNull(createdRoutine);
        assertEquals(1, createdRoutine.getId());
        assertEquals("safgsa", createdRoutine.getTitle());
        assertEquals(exercises, createdRoutine.getExercises());

    }



}


