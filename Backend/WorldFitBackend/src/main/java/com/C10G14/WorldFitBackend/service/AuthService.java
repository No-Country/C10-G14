package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.AuthenticationRequestDto;
import com.C10G14.WorldFitBackend.dto.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;

import java.io.IOException;

public interface AuthService {
    AuthenticationResponseDto register (RegisterRequestDto request) throws IOException;
    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
}
