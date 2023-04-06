package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Unit;
import com.C10G14.WorldFitBackend.enumeration.EUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {
    private long id;
    private String title;
    private String description;
    private String media;
    private String unit;

    public ExerciseDto(String title, String description, String media, String unit) {
        this.title = title;
        this.description = description;
        this.media = media;
        this.unit = unit;
    }

    public EUnit unitToEUnit() {
        switch (this.unit) {
            case "Kg":
                return EUnit.Kg;
            case "Km":
                return EUnit.Km;
            case "Minutos":
                return EUnit.Minutos;
            default:
                return EUnit.None;
        }


    }
}
