package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.StatisticDto;
import com.C10G14.WorldFitBackend.entity.Statistic;
import com.C10G14.WorldFitBackend.repository.StatisticRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticDtoMapper {
    StatisticRepository statisticRepository;
    public StatisticDto entityToDto (Statistic statistic) {
        return new StatisticDto(
                statistic.getId(),
                statistic.getDate(),
                statistic.getTotalIncome(),
                statistic.getTotalOutcome()
        );
    }

    public Statistic dtoToEntity (StatisticDto statisticDto) {
        return new Statistic(
                statisticDto.getDate(),
                statisticDto.getTotalIncome(),
                statisticDto.getTotalOutcome()
        );
    }

    public List<StatisticDto> entityListToDto (List<Statistic> statistics) {
        List<StatisticDto> statisticsDto = new ArrayList<>();
        for (Statistic statistic : statistics) {
            statisticsDto.add(entityToDto(statistic));
        }
        return statisticsDto;
    }
}
