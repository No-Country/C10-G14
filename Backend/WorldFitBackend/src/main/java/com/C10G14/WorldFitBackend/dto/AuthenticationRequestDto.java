package com.C10G14.WorldFitBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDto {
    String email;
    String password;
}
