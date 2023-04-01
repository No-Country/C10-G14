package com.C10G14.WorldFitBackend.security.jwt.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    String username;
    String password;
}
