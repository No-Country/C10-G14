package com.EstadisticaController;

import com.C10G14.WorldFitBackend.controller.EstadisticaController;
import com.C10G14.WorldFitBackend.dto.EstadisticaDto;
import com.C10G14.WorldFitBackend.entity.Estadistica;
import com.C10G14.WorldFitBackend.service.EstadisticaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstadisticaControllerTest {

    @InjectMocks
    private EstadisticaController estadisticaController;

    @Mock
    private EstadisticaService estadisticaService;

    @Test
    public void testGuardarEstadistica() {
        // crea objeto EstadisticaDto simulado
        EstadisticaDto estadisticaDto = new EstadisticaDto();
        estadisticaDto.setFecha("2023-04-04");
        estadisticaDto.setTotalIngresos(1000);
        estadisticaDto.setTotalEgresos(500);

        // crea objeto Estadistica simulado
        Estadistica estadisticaSimulado = new Estadistica();
        estadisticaSimulado.setId(1L);
        estadisticaSimulado.setFecha(LocalDate.parse(estadisticaDto.getFecha()));
        estadisticaSimulado.setTotalIngresos(estadisticaDto.getTotalIngresos());
        estadisticaSimulado.setTotalEgresos(estadisticaDto.getTotalEgresos());

        // define el comportamiento del servicio simulado
        when(estadisticaService.guardar(any(Estadistica.class))).thenReturn(estadisticaSimulado);

        // realiza la prueba
        Estadistica estadisticaGuardada = estadisticaController.guardarEstadistica(estadisticaDto);

        // verifica que se haya llamado el método guardar del servicio simulado
        verify(estadisticaService).guardar(any(Estadistica.class));

        // verifica que el objeto Estadistica retornado por el controlador sea igual al simulado
        assertEquals(estadisticaSimulado, estadisticaGuardada);
    }
}




