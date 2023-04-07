package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.exception.CantBeEmptyException;
import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.enumeration.ERole;
import com.C10G14.WorldFitBackend.exception.InputNotValidException;
import com.C10G14.WorldFitBackend.repository.RoleRepository;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AuthDtoMapper {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public User requestToEntity(RegisterRequestDto registerRequestDto){
        if (Objects.equals(registerRequestDto.getEmail(), "")
            || Objects.equals(registerRequestDto.getPassword(), "")
            || registerRequestDto.getEmail() == null
            || registerRequestDto.getPassword() == null){
            throw new CantBeEmptyException("Error: Email and password are required");
        }
        /*
        if (!registerRequestDto.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new InputNotValidException("Email direction isn't valid");
        }*/
        /*if (!registerRequestDto.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.@#$%^&+=])(?=\\S+$).{8,20}$")) {
            throw new InputNotValidException("Password must contain: at least 8 characters,1 digit, 1 upper case and 1 lower case letter and 1 special character");
        }*/

        if (userRepository.existsByEmail(registerRequestDto.getEmail())){
            throw new AlreadyExistException("Error: Email already taken");
        }
        List<Role> roles = new ArrayList<Role>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(()-> new RuntimeException("Role USER not found"));
        roles.add(userRole);
        return User.builder()
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .profileImg(registerRequestDto.getProfilePicture())
                .sex(registerRequestDto.getSex())
                .age(registerRequestDto.getAge())
                .height(registerRequestDto.getHeight())
                .weight(registerRequestDto.getWeight())
                .role(roles)
                .build();
    }
}
