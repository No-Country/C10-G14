package com.C10G14.WorldFitBackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class RegisterRequestDto {
    @NotBlank(message = "Email can't be empty")
    @Email (regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Email direction isn't valid")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @NotBlank(message = "Password can't be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "Password must contain: at least 8 characters,1 digit, 1 upper case and 1 lower case letter and 1 special character")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @URL(message = "URL not valid")
    private String profilePicture;
    @Pattern(regexp = "\\d{2,3}",
            message = "Weight must be in format 'kgs' example: '80'")
    private String weight;
    @Pattern(regexp = "^([0-2]*,)?[0-9]{2}+",
            message = "Height must be in format: 'mt,cms' example: '1,80' ")
    private String height;
    @Pattern(regexp = "[MmFf]",
            message = "Sex must be either m or f (no case sensitive)")
    private String sex;

    @Max(value = 110,
    message = "Maximum age is 110")
    private int age;
}
