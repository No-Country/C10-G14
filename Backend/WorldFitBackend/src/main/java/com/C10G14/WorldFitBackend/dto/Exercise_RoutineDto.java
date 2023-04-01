package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.entity.Exercise_Routine;
import com.C10G14.WorldFitBackend.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercise_RoutineDto {

    private long id;
    private String title;
    private String media;
    private String unit;
    private int quantity;
    private int series;
    private int repetitions;

    public Exercise_RoutineDto(Exercise exercise, int quantity, int series, int repetitions) {

        this.id = exercise.getId();
        this.title = exercise.getTitle();
        this.media = exercise.getMedia();
        this.unit = exercise.getUnit().getName().toString();
        this.quantity = quantity;
        this.series = series;
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return "\n{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", media='" + media + '\'' +
                ", unit=" + unit +
                ", quantity=" + quantity +
                ", series=" + series +
                ", repetitions=" + repetitions +
                '}';
    }
}
