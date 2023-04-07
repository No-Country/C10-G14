package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.enumeration.EUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponseDto {
    private long id;
    private String title;
    private String description;
    private String media;
    private String unit;

    public ExerciseResponseDto(String title, String description, String media, String unit) {
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
