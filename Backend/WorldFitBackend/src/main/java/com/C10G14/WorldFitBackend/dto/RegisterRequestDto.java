package com.C10G14.WorldFitBackend.dto;

import lombok.*;

@Getter
@Setter
public class RegisterRequestDto {
    private String username;
    private String password;
    private String profilePicture;
    private String weight;
    private String height;
    private String sex;
    private String age;
}
