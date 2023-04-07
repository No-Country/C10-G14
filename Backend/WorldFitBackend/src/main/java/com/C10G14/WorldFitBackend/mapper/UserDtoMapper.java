package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;
import com.C10G14.WorldFitBackend.dto.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.UserDto;
import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.enumeration.ERole;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.repository.RoleRepository;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoutineDtoMapper routineMapper;

    public UserDto entityToDto (User user) throws JsonProcessingException {

        List <RoutineResponseDto> routines = user.getRoutines().stream().map(
                (e) -> routineMapper.EntityToDto(e)
        ).toList();

        List <String> strRoles = user.getRole().stream().map(
                (r)-> r.getName().name()).toList();

        return new UserDto(user.getEmail(),
                user.getClientSince(),
                strRoles,
                user.getProfileImg(),
                user.getWeight(),
                user.getHeight(),
                user.getSex(),
                user.getAge(),
                routines);
    }

    public SimpleUserDto entityToSimpleDto (User user) throws JsonProcessingException {
        List <RoutineResponseDto> routines = user.getRoutines().stream().map(
                (e) -> routineMapper.EntityToDto(e)
        ).toList();

        return new SimpleUserDto(user.getEmail(),
                user.getClientSince(),
                user.getProfileImg(),
                routines);
    }

    public User dtoToEntity(UserDto dto) throws JsonProcessingException {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        user.setHeight(dto.getHeight());
        user.setProfileImg(dto.getProfileImg());
        user.setWeight(dto.getWeight());

        user.setRole(dto.getRoles().stream().map(
                (r) -> roleRepository.findByName(ERole.valueOf(r.toUpperCase()))
                        .orElseThrow(() -> new NotFoundException("Error: Role not found: " + r))
        ).toList());

        user.setRoutines(new HashSet<>());

        return user;
    }

    public List<UserDto> usersToDtoList(List<User> users) throws JsonProcessingException {
        List <UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(entityToDto(user));
        }
        return usersDto;
    }











}
