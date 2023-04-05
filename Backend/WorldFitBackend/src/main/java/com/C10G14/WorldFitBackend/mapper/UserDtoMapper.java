package com.C10G14.WorldFitBackend.mapper;

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

@Component
public class UserDtoMapper {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserDto entityToDto (User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> routineJsonList = new ArrayList<>();
        for (Routine routine : user.getRoutines()) {
            String routineJson = mapper.writeValueAsString(routine);
            routineJsonList.add(routineJson);
        }

        List<String> strRoles = new ArrayList<>();
        for (Role role : user.getRole()){
            strRoles.add(role.getName().name());
        }

        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        dto.setRoles((Set<String>) strRoles);
        dto.setHeight(user.getHeight());
        dto.setProfileImg(user.getProfileImg());
        dto.setWeight(user.getWeight());
        dto.setRoutines((Set<String>) routineJsonList);
        return dto;
    }

    public User dtoToEntity(UserDto dto) throws JsonProcessingException {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        user.setHeight(dto.getHeight());
        user.setProfileImg(dto.getProfileImg());
        user.setWeight(dto.getWeight());

        List<Role> roles = new ArrayList<>();
        for (String roleName : dto.getRoles()) {
            Role role = roleRepository.findByName(ERole.valueOf(roleName.toUpperCase()))
                    .orElseThrow(() -> new NotFoundException("Error: Role not found: " + roleName));
            roles.add(role);
        }
        user.setRole(roles);

        Set<Routine> routines = new HashSet<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String routineJson : dto.getRoutines()) {
            Routine routine = mapper.readValue(routineJson, Routine.class);
            routines.add(routine);
        }
        user.setRoutines(routines);

        return user;
    }









}
