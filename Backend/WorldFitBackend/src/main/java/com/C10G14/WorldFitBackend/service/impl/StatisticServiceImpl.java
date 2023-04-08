package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.entity.Statistic;
import com.C10G14.WorldFitBackend.repository.StatisticRepository;
import com.C10G14.WorldFitBackend.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    StatisticRepository statisticRepository;


    @Override
    public Statistic save(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    @Override
    public List<Statistic> getAll() {
        return statisticRepository.findAll();
    }

    @Override
    public Statistic getById(Long id) {
        return statisticRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        statisticRepository.deleteById(id);
    }
}
