package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.entity.Estadistica;

import java.util.List;

public interface EstadisticaService {

    Estadistica guardar(Estadistica estadistica);
    List<Estadistica> buscarTodas();
    Estadistica buscarPorId(Long id);
    void eliminarPorId(Long id);

}
