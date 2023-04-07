package com.C10G14.WorldFitBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    String email;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    String password;
}
