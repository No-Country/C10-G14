package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
import com.C10G14.WorldFitBackend.dto.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.UserDto;
import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.enumeration.ERole;
import com.C10G14.WorldFitBackend.enumeration.ESex;
import com.C10G14.WorldFitBackend.exception.ForbiddenException;
import com.C10G14.WorldFitBackend.exception.InputNotValidException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.mapper.UserDtoMapper;
import com.C10G14.WorldFitBackend.repository.RoleRepository;
import com.C10G14.WorldFitBackend.repository.RoutineRepository;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import com.C10G14.WorldFitBackend.service.ImageService;
import com.C10G14.WorldFitBackend.service.UserService;
import com.C10G14.WorldFitBackend.util.DtoFormatter;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ImageService imageService;
    private final UserDtoMapper mapper;
    private final DtoFormatter formatter;

    @Transactional
    @Override
    public List<UserDto> getAllUsers() throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        List<UserDto> users = new ArrayList<>();
        if (roles.contains("ROLE_ADMIN")) {
            users.addAll(mapper.usersToDtoList(userRepository.findAll()));
        } else if (roles.contains("ROLE_COACH")) {
            users.addAll(this.getByRole("user"));
            users.addAll(this.getByRole("customer"));
        }
        return users;
    }

    @Transactional
    @Override
    public UserDto getUserById(Long id) throws JsonProcessingException {
        return mapper.entityToDto(userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Error: user not found")));
    }

    @Transactional
    @Override
    public SimpleUserDto getSimpleUserById(Long id) throws JsonProcessingException {
        return mapper.entityToSimpleDto(userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Error: user not found")));
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) throws JsonProcessingException {
        User user = mapper.dtoToEntity(userDto);
        userRepository.save(user);
        return mapper.entityToDto(userRepository.findByEmail(userDto.getEmail()).orElseThrow( () ->
                new RuntimeException("There is a problem creating the user")));
    }

    @Transactional
    @Override
    public UserDto updateUser(Long id, RegisterRequestDto userDto) throws IOException {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Error: user not found"));

        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getProfileImg() != null){
        if(imageService.checkImage(userDto.getProfileImg())){
            user.setProfileImg(imageService.uploadImage(
                    userDto.getProfileImg(),
                    user.getEmail()
                    ));
        }
        }
        if (userDto.getName() != null && !userDto.getName().isEmpty()) {
            user.setName(formatter.formatName(userDto.getName()));
        }
        if (userDto.getAge()>0&&userDto.getAge()<110) {
           user.setAge(userDto.getAge());
        }
        if (userDto.getSex() != null && !userDto.getSex().isEmpty()) {
            if (userDto.getSex().equalsIgnoreCase("male"))
                user.setSex(ESex.MALE);
            else if (userDto.getSex().equalsIgnoreCase("female"))
                user.setSex(ESex.FEMALE);
            else throw new InputNotValidException("Invalid gender, must be male or female");
        }
        if (userDto.getWeight() != null) {
            user.setWeight(userDto.getWeight());
        }
        if (userDto.getHeight() != null) {
            user.setHeight(userDto.getHeight());
        }
        if (userDto.getObjective() != null && !userDto.getObjective().isEmpty()) {
            user.setObjective(userDto.getObjective());
        }
        if (userDto.getMedical_indication() != null && !userDto.getMedical_indication().isEmpty()) {
            user.setMedical_indication(userDto.getMedical_indication());
        }
        userRepository.save(user);
        return mapper.entityToDto(user);
    }
    @Transactional
    @Override
    public UserDto updateRole(Long id, String requestRole) throws JsonProcessingException {
        User user = userRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Error: User not found"));
        if (user.getRole().contains(roleRepository.findByName(ERole.ROLE_ADMIN).get()))
            throw new ForbiddenException("Error: Cant change admin role");

        ERole roleName = Role.RoletoERole(requestRole);
        if (roleName == null) {
            throw new NotFoundException("Error: Role not found");
        }
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        user.getRole().clear();
        user.getRole().add(role);
        userRepository.save(user);
        return mapper.entityToDto(user);
    }

    @Transactional
    @Override
    public List<UserDto> getByRole(String requestRole) throws JsonProcessingException {
        ERole roleName = Role.RoletoERole(requestRole);
        if (roleName == null) {
            throw new NotFoundException("Error: Role not found");
        }
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        return mapper.usersToDtoList(userRepository.findByRole(role).orElseThrow(() ->
                new RuntimeException("Error retrieving role " + requestRole)));
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
