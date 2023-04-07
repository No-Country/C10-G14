package com.C10G14.WorldFitBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT token returned",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Email is required",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: Password is required",
                    content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody
                                                                  RegisterRequestDto request) {
        AuthenticationResponseDto registerResponse = authService.register(request);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @Operation(summary = "Authenticate an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT token returned",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Email is required",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: Password is required",
                    content = @Content)})
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(@RequestBody
                                                                      AuthenticationRequestDto request) {
        AuthenticationResponseDto authResponse = authService.authenticate(request);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}