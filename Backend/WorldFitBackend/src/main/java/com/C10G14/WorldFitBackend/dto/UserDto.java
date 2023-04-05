package com.C10G14.WorldFitBackend.dto;

import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.entity.Routine;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private String clientSince;
    private List<String> roles;
    private String profileImg;
    private String weight;
    private String height;
    private String sex;
    private String age;
    private List<String> routines;
}
