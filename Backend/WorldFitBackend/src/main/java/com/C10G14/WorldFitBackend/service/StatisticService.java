package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.StatisticDateDto;
import com.C10G14.WorldFitBackend.dto.StatisticDto;
import com.C10G14.WorldFitBackend.entity.Statistic;

import java.util.List;

public interface StatisticService {

    StatisticDto save(StatisticDto statistic);

    List<StatisticDto> getAll();
    StatisticDto getById(Long id);
    List<StatisticDto> getByDate(StatisticDateDto date);
    List<StatisticDto> getByDateBetween(StatisticDateDto dates);
    void deleteById(Long id);
    void deleteByDateBetween(StatisticDateDto date);
}
