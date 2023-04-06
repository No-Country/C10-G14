package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.entity.Routine;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SimpleUserDto {
    private String email;
    private String clientSince;
    private String profileImg;
    private List<RoutineResponseDto> routines;
}
