package com.AuthController;

import com.C10G14.WorldFitBackend.controller.AuthController;
import com.C10G14.WorldFitBackend.dto.AuthenticationRequestDto;
import com.C10G14.WorldFitBackend.dto.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
import com.C10G14.WorldFitBackend.service.impl.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthServiceImpl authService;

    private RegisterRequestDto registerRequestDto;
    private AuthenticationRequestDto authenticationRequestDto;

    @Before
    public void setup() {
        registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setEmail("test@example.com");
        registerRequestDto.setPassword("testpassword");

        authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setUsername("test@example.com");
        authenticationRequestDto.setPassword("testpassword");
    }

    @Test
    public void testRegisterUser() {
        AuthenticationResponseDto expectedResponse = new AuthenticationResponseDto();
        expectedResponse.setToken("testtoken");

        when(authService.register(any(RegisterRequestDto.class))).thenReturn(expectedResponse);

        ResponseEntity<?> result = authController.registerUser(registerRequestDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(expectedResponse, result.getBody());
    }

    @Test
    public void testAuthenticateUser() {
        AuthenticationResponseDto expectedResponse = new AuthenticationResponseDto();
        expectedResponse.setToken("testtoken");

        when(authService.authenticate(any(AuthenticationRequestDto.class))).thenReturn(expectedResponse);

        ResponseEntity<AuthenticationResponseDto> result = authController.authenticateUser(authenticationRequestDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(expectedResponse, result.getBody());
    }
}


