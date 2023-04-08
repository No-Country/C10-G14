package com.RoutineController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineDto;
import com.C10G14.WorldFitBackend.dto.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.entity.Unit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
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

    @Test
    public void testGetAllRoutines() {
        // Preparación de datos
        List<RoutineResponseDto> routines = new ArrayList<>();
        routines.add(new RoutineResponseDto(1L, "Rutina 1", new HashSet<>()));
        routines.add(new RoutineResponseDto(2L, "Rutina 2", new HashSet<>()));
        routines.add(new RoutineResponseDto(3L, "Rutina 3", new HashSet<>()));

        when(routineService.getAllRoutines()).thenReturn(routines);

        // Ejecución del método a probar
        ResponseEntity<List<RoutineResponseDto>> response = routineController.getAllRoutines();

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(routines.size(), response.getBody().size());
        assertTrue(routines.containsAll(response.getBody()));
    }

    @Test
    public void testGetRoutineById() {
        // Preparación de datos
        long id = 1L;
        Set<Exercise_RoutineDto> exercises = new HashSet<>();
        exercises.add(new Exercise_RoutineDto(1L, "Ejercicio 1", "Descripción del ejercicio 1", "https://url-ejercicio-1.com", "Kg", "Peso", 3, 10, 3, new ArrayList<>()));
        exercises.add(new Exercise_RoutineDto(2L, "Ejercicio 2", "Descripción del ejercicio 2", "https://url-ejercicio-2.com", "Km", "Distancia", 5, 5, 2, new ArrayList<>()));

        RoutineResponseDto expectedRoutine = new RoutineResponseDto(id, "Rutina 1", exercises);

        when(routineService.getRoutineById(id)).thenReturn(expectedRoutine);

        // Ejecución del método a probar
        ResponseEntity<RoutineResponseDto> response = routineController.getRoutineById(id);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedRoutine, response.getBody());
    }
    @Test
    public void testUpdateRoutine() {
        // Preparación de datos
        long id = 1L;
        long userId = 2L;
        String title = "Nueva Rutina";
        Set<Long> exerciseIds = new HashSet<>(Arrays.asList(1L, 2L));
        Set<Exercise_RoutineDto> exercises = new HashSet<>();
        exercises.add(new Exercise_RoutineDto(1L, "Ejercicio 1", "Descripción del ejercicio 1", "https://url-ejercicio-1.com", "Kg", "Peso", 3, 10, 3, new ArrayList<>()));
        exercises.add(new Exercise_RoutineDto(2L, "Ejercicio 2", "Descripción del ejercicio 2", "https://url-ejercicio-2.com", "Km", "Distancia", 5, 5, 2, new ArrayList<>()));

        RoutineRequestDto routineToUpdate = new RoutineRequestDto(id, userId, title, exercises);

        RoutineResponseDto expectedRoutine = new RoutineResponseDto(id, title, exercises);

        when(routineService.updateRoutine(id, routineToUpdate)).thenReturn(expectedRoutine);

        // Ejecución del método a probar
        ResponseEntity<RoutineResponseDto> response = routineController.updateRoutine(id, routineToUpdate);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedRoutine, response.getBody());
    }

    @Test
    public void testDeleteRoutine() {
        // Preparación de datos
        long id = 1L;

        // Ejecución del método a probar
        ResponseEntity<Void> response = routineController.deleteRoutine(id);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

        // Verificación de que se llamó al servicio con el ID correcto
        verify(routineService, times(1)).deleteRoutine(id);
    }





        @Test
        public void testAddExercise() {
            // Preparación de datos
            long routineId = 1L;
            Exercise_RoutineRequestDto exerciseToAdd = new Exercise_RoutineRequestDto(1L, 3, 10, 3);

            Set<Exercise_RoutineDto> exercises = new HashSet<>();
            exercises.add(new Exercise_RoutineDto(1L, "Ejercicio 1", "Descripción del ejercicio 1", "https://url-ejercicio-1.com", "Kg", "Peso", 3, 10, 3, new ArrayList<>()));
            exercises.add(new Exercise_RoutineDto(2L, "Ejercicio 2", "Descripción del ejercicio 2", "https://url-ejercicio-2.com", "Km", "Distancia", 5, 5, 2, new ArrayList<>()));

            RoutineResponseDto expectedRoutine = new RoutineResponseDto(routineId, "Rutina 1", exercises);

            when(routineService.addExercise(routineId, exerciseToAdd)).thenReturn(expectedRoutine);

            // Ejecución del método a probar
            ResponseEntity<RoutineResponseDto> response = routineController.addExercise(routineId, exerciseToAdd);

            // Verificación del resultado
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(expectedRoutine, response.getBody());
        }



    @Test
    public void testUpdateExercise() {
        // Preparación de datos
        long routineId = 1L;
        Exercise_RoutineRequestDto exerciseToUpdate = new Exercise_RoutineRequestDto(1L, 3, 10, 3);

        Set<Exercise_RoutineDto> exercises = new HashSet<>();
        exercises.add(new Exercise_RoutineDto(1L, "Ejercicio 1", "Descripción del ejercicio 1", "https://url-ejercicio-1.com", "Kg", "Peso", 3, 10, 3, new ArrayList<>()));
        exercises.add(new Exercise_RoutineDto(2L, "Ejercicio 2", "Descripción del ejercicio 2", "https://url-ejercicio-2.com", "Km", "Distancia", 5, 5, 2, new ArrayList<>()));

        RoutineResponseDto expectedRoutine = new RoutineResponseDto(routineId, "Rutina de prueba", exercises);

        when(routineService.updateExercise(routineId, exerciseToUpdate)).thenReturn(expectedRoutine);

        // Ejecución del método a probar
        ResponseEntity<RoutineResponseDto> response = routineController.updateExercise(routineId, exerciseToUpdate);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedRoutine, response.getBody());
    }
    @Test
    public void testRemoveExercise() {
        // Preparación de datos
        long routineId = 1L;
        long exerciseId = 2L;

        Map<String, Long> exerciseMap = new HashMap<>();
        exerciseMap.put("exerciseId", exerciseId);

        Set<Exercise_RoutineDto> exercises = new HashSet<>();
        exercises.add(new Exercise_RoutineDto(1L, "Ejercicio 1", "Descripción del ejercicio 1", "https://url-ejercicio-1.com", "Kg", "Peso", 3, 10, 3, new ArrayList<>()));
        exercises.add(new Exercise_RoutineDto(2L, "Ejercicio 2", "Descripción del ejercicio 2", "https://url-ejercicio-2.com", "Km", "Distancia", 5, 5, 2, new ArrayList<>()));

        RoutineResponseDto expectedRoutine = new RoutineResponseDto(routineId, "Rutina de Ejercicios", exercises);

        when(routineService.removeExercise(routineId, exerciseId)).thenReturn(expectedRoutine);

        // Ejecución del método a probar
        ResponseEntity<RoutineResponseDto> response = routineController.removeExercise(routineId, exerciseMap);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedRoutine, response.getBody());
    }

}







