package com.C10G14.WorldFitBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@Builder
public class RegisterRequestDto {
    @NotBlank(message = "Email is required")
    @Email (regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Email direction isn't valid")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^([a-zA-Z0-9,]){8,20}$",
            message = "Password must contain at least 8 characters including letters, numbers, spaces and commas")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @URL(message = "image URL not valid")
    private String profileImg;
    @DecimalMin(value = "0.00", message = "Weight must be greater than or equal to zero")
    @DecimalMax(value = "999.99", message = "Weight must have two or fewer decimal places")
    private Double weight;
    @DecimalMin(value = "0.00", message = "Height must be greater than or equal to zero")
    @DecimalMax(value = "999.99", message = "Height must have two or fewer decimal places")
    private Double height;
    @Pattern(regexp = "(?i)^(male|female)$",
            message = "Sex must be either male or female (no case sensitive)")
    private String sex;

    @Max(value = 110,
    message = "Maximum age is 110")
    private int age;
}
