package com.AuthServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import com.C10G14.WorldFitBackend.dto.AuthenticationRequestDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.C10G14.WorldFitBackend.dto.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.mapper.AuthDtoMapper;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import com.C10G14.WorldFitBackend.security.jwt.JwtService;
import com.C10G14.WorldFitBackend.service.impl.AuthServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AuthDtoMapper authMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void register_ValidRequest_ShouldReturnAuthenticationResponseDtoWithToken() {
        // Arrange
        RegisterRequestDto requestDto = new RegisterRequestDto();
        requestDto.setEmail("test@test.com");
        requestDto.setPassword("testpassword");
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        when(authMapper.requestToEntity(requestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        String jwtToken = "testjwttoken";
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        // Act
        AuthenticationResponseDto responseDto = authService.register(requestDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals(jwtToken, responseDto.getToken());
        verify(authMapper, times(1)).requestToEntity(requestDto);
        verify(userRepository, times(1)).save(user);
        verify(jwtService, times(1)).generateToken(user);
    }

    @Test
    public void authenticateTest() {
        AuthenticationRequestDto request = new AuthenticationRequestDto();
        request.setUsername("testuser@example.com");
        request.setPassword("testpassword");

        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("encodedpassword");

        when(userRepository.findByEmail("testuser@example.com")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("testtoken");

        AuthenticationResponseDto expectedResponse = new AuthenticationResponseDto();
        expectedResponse.setToken("testtoken");

        AuthenticationResponseDto response = authService.authenticate(request);

        verify(authenticationManager, times(1)).authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        verify(userRepository, times(1)).findByEmail(request.getUsername());
        verify(jwtService, times(1)).generateToken(user);

        Assertions.assertEquals(expectedResponse.getToken(), response.getToken());
    }

}


