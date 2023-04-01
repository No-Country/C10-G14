package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {
    private long id;
    private String title;
    private String media;
    private String unit;

    public ExerciseDto(String title, String media, String unit) {
        this.title = title;
        this.media = media;
        this.unit = unit;
    }
}
