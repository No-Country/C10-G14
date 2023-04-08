package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.entity.Statistic;

import java.util.List;

public interface StatisticService {

    Statistic save(Statistic statistic);
    List<Statistic> getAll();
    Statistic getById(Long id);
    void deleteById(Long id);

}
