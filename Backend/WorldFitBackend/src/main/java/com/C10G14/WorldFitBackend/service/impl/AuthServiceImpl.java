package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.enumeration.ERole;
import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.mapper.AuthDtoMapper;
import com.C10G14.WorldFitBackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthDtoMapper authMapper;
    @Autowired
    private UserRepository userRepository;
    //Sin docker
    //private final String FOLDER_PATH=System.getProperty("user.dir")+"/src/main/resources/static/images/";
    //Con docker
    private  final String FOLDER_PATH= "/var/lib/images/";

    //dev
    private final String URL_PATH="localhost:8080/content/images/";

    //prod
    //private final String URL_PATH="api.worldfit.site/content/images/";


    @Override
    public AuthenticationResponseDto register(RegisterRequestDto request) throws IOException {
        if (userRepository.existsByEmail(request.getEmail().toLowerCase())){
            throw new AlreadyExistException("Error: Email already taken");
        }
        User newUser = authMapper.requestToEntity(request);

        if (!Objects.equals(request.getProfileImg(),null)){
        String imgPath = FOLDER_PATH+request.getProfileImg().getOriginalFilename();
        request.getProfileImg().transferTo(new File(imgPath));
        newUser.setProfileImg(URL_PATH + request.getProfileImg().getOriginalFilename());
        }

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
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userrepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Error: Username not found"));
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponseDto authResponse = new AuthenticationResponseDto();
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}
