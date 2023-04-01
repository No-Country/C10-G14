package security.jwt.auth.service;

import security.jwt.auth.AuthenticationRequest;
import security.jwt.auth.AuthenticationResponse;
import security.jwt.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register (RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
