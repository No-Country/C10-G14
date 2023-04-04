package com.C10G14.WorldFitBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.C10G14.WorldFitBackend.dto.AuthenticationRequestDto;
import com.C10G14.WorldFitBackend.dto.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
import com.C10G14.WorldFitBackend.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody
                                                                  RegisterRequestDto request) {
        AuthenticationResponseDto registerResponse = authService.register(request);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(@RequestBody
                                                                      AuthenticationRequestDto request) {
        AuthenticationResponseDto authResponse = authService.authenticate(request);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}