package com.EstadisticaServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import com.C10G14.WorldFitBackend.entity.Estadistica;
import com.C10G14.WorldFitBackend.repository.EstadisticaRepository;
import com.C10G14.WorldFitBackend.service.impl.EstadisticaServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class EstadisticaServiceImplTest {

    @Mock
    private EstadisticaRepository estadisticaRepository;

    @InjectMocks
    private EstadisticaServiceImpl estadisticaService;

    @Test
    public void testGuardar() {
        // Crear un objeto Estadistica para usar en la prueba
        Estadistica estadistica = new Estadistica();

        // Configurar el comportamiento esperado del mock de EstadisticaRepository
        when(estadisticaRepository.save(estadistica)).thenReturn(estadistica);

        // Llamar al método a probar
        Estadistica resultado = estadisticaService.guardar(estadistica);

        // Verificar que el resultado es el esperado
        assertEquals(estadistica, resultado);

        // Verificar que se llamó al método save del mock de EstadisticaRepository
        verify(estadisticaRepository).save(estadistica);
    }

        @Test
        public void buscarTodas_DebeRetornarListaDeEstadisticas() {
            //Arrange
            List<Estadistica> estadisticasMock = new ArrayList<>();
            LocalDate fecha = LocalDate.of(2022, 5, 10);
            estadisticasMock.add(new Estadistica( fecha, 424, 100));
            estadisticasMock.add(new Estadistica( fecha, 1234, 200));

            when(estadisticaRepository.findAll()).thenReturn(estadisticasMock);

            //Act
            List<Estadistica> estadisticas = estadisticaService.buscarTodas();

            //Assert
            assertEquals(estadisticasMock, estadisticas);
            verify(estadisticaRepository).findAll();
        }

       @Test
       public void testBuscarPorId() {
        Estadistica estadisticaMock = new Estadistica(LocalDate.of(2022, 5, 1), 100, 50);
        Long id = 1L;
        Mockito.when(estadisticaRepository.findById(id)).thenReturn(Optional.of(estadisticaMock));
        Estadistica result = estadisticaService.buscarPorId(id);
        assertNotNull(result);
        assertEquals(estadisticaMock.getId(), result.getId());
        assertEquals(estadisticaMock.getFecha(), result.getFecha());
        assertEquals(estadisticaMock.getTotalIngresos(), result.getTotalIngresos(), 0);
        assertEquals(estadisticaMock.getTotalEgresos(), result.getTotalEgresos(), 0);
    }

    @Test
    public void testEliminarPorId() {
        Long id = 1L;
        doNothing().when(estadisticaRepository).deleteById(id);
        estadisticaService.eliminarPorId(id);
        verify(estadisticaRepository, times(1)).deleteById(id);
    }


}
