package com.C10G14.WorldFitBackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private String clientSince;
    private Set<String> roles;
    private String profileImg;
    private String weight;
    private String height;
    private String sex;
    private String age;
    private Set<String> routines;
}

