package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.enumeration.EUnit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "title is required")
    private String title;
    private String description;
    @URL(message = "media URL not valid")
    private String media;
    private String unit;

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