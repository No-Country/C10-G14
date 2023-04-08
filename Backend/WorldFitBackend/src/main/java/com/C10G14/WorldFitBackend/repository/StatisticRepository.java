package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    List<Statistic> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}

