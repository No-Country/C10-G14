package com.ExerciseController;

import com.C10G14.WorldFitBackend.controller.ExerciseController;
import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExerciseControllerTest {

    @InjectMocks
    private ExerciseController exerciseController;

    @Mock
    private ExerciseService exerciseService;

    @Test
    public void testGetAllExercises() {
        // crea objetos ExerciseDto simulados
        ExerciseDto exercise1 = new ExerciseDto();
        exercise1.setId(1L);
        exercise1.setTitle("Press Banca");
        ExerciseDto exercise2 = new ExerciseDto();
        exercise2.setId(2L);
        exercise2.setTitle("Sentadillas");

        // define el comportamiento del servicio simulado
        when(exerciseService.getAllExercises()).thenReturn(Arrays.asList(exercise1, exercise2));

        // realiza la prueba
        assertEquals(Arrays.asList(exercise1, exercise2), exerciseController.getAllExercises().getBody());

        // verifica que se haya llamado el método getAllExercises del servicio simulado
        verify(exerciseService).getAllExercises();
    }

    @Test
    public void testGetExerciseById() {
        // crea objeto ExerciseDto simulado
        ExerciseDto exercise = new ExerciseDto();
        exercise.setId(1L);
        exercise.setTitle("Press Banca");

        // define el comportamiento del servicio simulado
        when(exerciseService.getExerciseById(1L)).thenReturn(exercise);

        // realiza la prueba
        assertEquals(exercise, exerciseController.getExerciseById(1L).getBody());

        // verifica que se haya llamado el método getExerciseById del servicio simulado con el parámetro correcto
        verify(exerciseService).getExerciseById(1L);
    }

    @Test
    public void testCreateExercise() {
        // crea objeto ExerciseDto simulado
        ExerciseDto exercise = new ExerciseDto();
        exercise.setId(1L);
        exercise.setTitle("Press Banca");

        // define el comportamiento del servicio simulado
        when(exerciseService.createExercise(any(ExerciseDto.class))).thenReturn(exercise);

        // realiza la prueba
        assertEquals(exercise, exerciseController.createExercise(exercise).getBody());

        // verifica que se haya llamado el método createExercise del servicio simulado con el parámetro correcto
        verify(exerciseService).createExercise(any(ExerciseDto.class));
    }

    @Test
    public void testUpdateExercise() {
        // crea objeto ExerciseDto simulado
        ExerciseDto exercise = new ExerciseDto();
        exercise.setId(1L);
        exercise.setTitle("Press Banca");

        // define el comportamiento del servicio simulado
        when(exerciseService.updateExercise(eq(1L), any(ExerciseDto.class))).thenReturn(exercise);

        // realiza la prueba
        assertEquals(exercise, exerciseController.updateExercise(1L, exercise).getBody());

        // verifica que se haya llamado el método updateExercise del servicio simulado con los parámetros correctos
        verify(exerciseService).updateExercise(eq(1L), any(ExerciseDto.class));
    }
}

