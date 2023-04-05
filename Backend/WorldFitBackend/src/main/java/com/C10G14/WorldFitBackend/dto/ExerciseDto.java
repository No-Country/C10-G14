package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Unit;
import com.C10G14.WorldFitBackend.enumeration.EUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    public EUnit unitToEUnit (){
        return  (this.unit == null) ? EUnit.None :
                (this.unit.equals("") ? EUnit.None :
                        (this.unit.equals("Kg")? EUnit.Kg :
                                (this.unit.equals("Km") ? EUnit.Km :
                                        (this.unit.equals("Minutos") ? EUnit.Minutos : null
                                        )
                                )
                         )
                );

    }
}
