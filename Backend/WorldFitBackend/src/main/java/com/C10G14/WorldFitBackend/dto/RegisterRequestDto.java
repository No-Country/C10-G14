package com.C10G14.WorldFitBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
public class RegisterRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    private String profilePicture;
    private String weight;
    private String height;
    private String sex;
    private String age;
}
