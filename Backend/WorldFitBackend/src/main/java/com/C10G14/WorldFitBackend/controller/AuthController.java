package com.C10G14.WorldFitBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.C10G14.WorldFitBackend.security.jwt.auth.AuthenticationRequest;
import com.C10G14.WorldFitBackend.security.jwt.auth.AuthenticationResponse;
import com.C10G14.WorldFitBackend.security.jwt.auth.RegisterRequest;
import com.C10G14.WorldFitBackend.security.jwt.service.AuthServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody
                                                               RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody
                                                                   AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}