package com.C10G14.WorldFitBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SimpleUserDto {
    private String email;
    private String clientSince;
    private String profileImg;
    private List<RoutineResponseDto> routines;
}
