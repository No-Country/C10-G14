package com.C10G14.WorldFitBackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String email;
    private String clientSince;
    private String name;
    private List<String> roles;
    private String profileImg;
    private Double weight;
    private Double height;
    private String sex;
    private Integer age;
    private List<RoutineResponseDto> routines;
}
