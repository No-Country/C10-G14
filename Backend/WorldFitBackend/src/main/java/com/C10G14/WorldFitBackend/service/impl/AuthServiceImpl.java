package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.enumeration.ERole;
import com.C10G14.WorldFitBackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.C10G14.WorldFitBackend.dto.AuthenticationRequestDto;
import com.C10G14.WorldFitBackend.dto.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.repository.RoleRepository;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.C10G14.WorldFitBackend.security.jwt.JwtService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;




    @Override
    public AuthenticationResponseDto register(RegisterRequestDto request) {
        List<Role> roles = new ArrayList<Role>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(()-> new RuntimeException("Role USER not found"));
        roles.add(userRole);

        User newUser = User.builder()
                .email(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImg(request.getProfilePicture())
                .role(roles)
                .build();
        userrepository.save(newUser);
        String jwtToken = jwtService.generateToken(newUser);
        AuthenticationResponseDto authResponse = new AuthenticationResponseDto();
        authResponse.setToken(jwtToken);
        return authResponse ;
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userrepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User already authenticated"));
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponseDto authResponse = new AuthenticationResponseDto();
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}
