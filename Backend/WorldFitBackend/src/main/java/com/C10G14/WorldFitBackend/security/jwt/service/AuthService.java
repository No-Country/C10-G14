package com.C10G14.WorldFitBackend.security.jwt.service;

import com.C10G14.WorldFitBackend.security.jwt.auth.AuthenticationRequest;
import com.C10G14.WorldFitBackend.security.jwt.auth.AuthenticationResponse;
import com.C10G14.WorldFitBackend.security.jwt.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register (RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
