package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Exercise_RoutineDto {

    private long id;
    private String title;


    private String description;
    private String media;
    private String type;
    private String unit;
    private int quantity;
    private int repetitions;
    private int seriesNumber;
    private List series;
    //private int[][] series;


    public Exercise_RoutineDto(Exercise exercise, int quantity, int repetitions, int seriesNumber) {

        this.id = exercise.getId();
        this.title = exercise.getTitle();
        this.description = exercise.getDescription();
        this.media = exercise.getMedia();
        String unitName = "";
        if (exercise.getUnit() != null) {
            unitName = exercise.getUnit().getName().toString();
        }
        if (unitName.equals("None")) {
            unitName = "";
        }
        this.unit = unitName;

        this.type = (this.unit.equals("Km")) ? "Distancia" :
                (this.unit.equals("Kg")) ? "Peso" : "";
        this.quantity = quantity;
        this.repetitions = repetitions;
        this.seriesNumber = seriesNumber;
        this.series = new ArrayList<>();
        for (int i = 0; i < seriesNumber; i++) {
            series.add(new Object());
        }
    }

    @Override
    public String toString() {
        return "\n{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", media='" + media + '\'' +
                ", unit=" + unit +
                ", quantity=" + quantity +
                ", series=" + seriesNumber +
                ", repetitions=" + repetitions +
                '}';
    }
    public void setName(String name) {
        this.title = name;
    }

}



