package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.UserDto;
import com.C10G14.WorldFitBackend.entity.Role;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.enumeration.ERole;
import com.C10G14.WorldFitBackend.exception.ForbiddenException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.mapper.UserDtoMapper;
import com.C10G14.WorldFitBackend.repository.RoleRepository;
import com.C10G14.WorldFitBackend.repository.RoutineRepository;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import com.C10G14.WorldFitBackend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDtoMapper mapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoutineRepository routineRepository;

    @Transactional
    @Override
    public List<UserDto> getAllUsers() throws JsonProcessingException {
        return mapper.usersToDtoList(userRepository.findAll());
    }

    @Transactional
    @Override
    public UserDto getUserById(Long id) throws JsonProcessingException {
        return mapper.entityToDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("Error: user not found")));
    }

    @Transactional
    @Override
    public SimpleUserDto getSimpleUserById(Long id) throws JsonProcessingException {
        return mapper.entityToSimpleDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("Error: user not found")));
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) throws JsonProcessingException {
        User user = mapper.dtoToEntity(userDto);
        userRepository.save(user);
        return mapper.entityToDto(userRepository.findByEmail(userDto.getEmail()).orElseThrow( () -> new RuntimeException("There is a problem creating the user")));
    }

    @Transactional
    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws JsonProcessingException {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Error: user not found"));
        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getProfileImg() != null && !userDto.getProfileImg().isEmpty()) {
            user.setProfileImg(user.getProfileImg());
        }
        if (userDto.getAge() != null && !userDto.getAge().isEmpty()) {
            user.setAge(userDto.getAge());
        }
        if (userDto.getSex() != null && !userDto.getSex().isEmpty()) {
            user.setSex(userDto.getSex());
        }
        if (userDto.getHeight() != null && !userDto.getHeight().isEmpty()) {
            user.setHeight(userDto.getHeight());
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
        Map<String, ERole> roleMap = Map.of(
                "user", ERole.ROLE_USER,
                "couch", ERole.ROLE_COUCH,
                "customer", ERole.ROLE_CUSTOMER,
                "admin", ERole.ROLE_ADMIN);
        ERole roleName = roleMap.get(requestRole);
        if (roleName == null) {
            throw new NotFoundException("Error: Role not found");
        }
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRole(roles);

        userRepository.save(user);
        return mapper.entityToDto(user);
    }

    @Transactional
    @Override
    public List<UserDto> getByRole(String requestRole) throws JsonProcessingException {
        Map<String, ERole> roleMap = Map.of(
                "user", ERole.ROLE_USER,
                "couch", ERole.ROLE_COUCH,
                "customer", ERole.ROLE_CUSTOMER,
                "admin", ERole.ROLE_ADMIN);
        ERole roleName = roleMap.get(requestRole);
        if (roleName == null) {
            throw new NotFoundException("Error: Role not found");
        }
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        return mapper.usersToDtoList(userRepository.findByRole(role).orElseThrow(() -> new RuntimeException("Error retrieving role " + requestRole)));
    }

    @Transactional
    @Override
    public UserDto addRoutine(Long id, Long routineId) throws JsonProcessingException {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Error: User not found "));
        Routine routine = routineRepository.findById(routineId).orElseThrow(() -> new NotFoundException("Error: Routine not found"));
        Set<Routine> userRoutines = user.getRoutines();
        userRoutines.add(routine);
        userRepository.save(user);
        return mapper.entityToDto(user);
    }

    @Transactional
    @Override
    public UserDto deleteRoutine(Long id, Long routineId) throws JsonProcessingException {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Error: User not found "));
        Routine routine = routineRepository.findById(routineId).orElseThrow(() -> new NotFoundException("Error: Routine not found"));
        Set<Routine> userRoutines = user.getRoutines();
        if (!userRoutines.contains(routine)){
            throw new NotFoundException("Error: Routine Id not found in user " + user.getEmail());
        }else{
            userRoutines.remove(routine);
            userRepository.save(user);
        }
        return mapper.entityToDto(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
