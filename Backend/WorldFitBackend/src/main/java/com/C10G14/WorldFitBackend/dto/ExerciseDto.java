package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseDto {
    private long id;
    private String title;
    private String media;
    private Unit unit;

    public ExerciseDto(String title, String media, Unit unit) {
        this.title = title;
        this.media = media;
        this.unit = unit;
    }
}
