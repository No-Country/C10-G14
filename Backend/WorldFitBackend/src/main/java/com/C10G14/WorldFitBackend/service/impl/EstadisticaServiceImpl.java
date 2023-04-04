package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.entity.Estadistica;
import com.C10G14.WorldFitBackend.repository.EstadisticaRepository;
import com.C10G14.WorldFitBackend.service.EstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    @Autowired
    private EstadisticaRepository estadisticaRepository;

    @Override
    public Estadistica guardar(Estadistica estadistica) {
        return estadisticaRepository.save(estadistica);
    }

    @Override
    public List<Estadistica> buscarTodas() {
        return estadisticaRepository.findAll();
    }

    @Override
    public Estadistica buscarPorId(Long id) {
        return estadisticaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPorId(Long id) {
        estadisticaRepository.deleteById(id);
    }
}

