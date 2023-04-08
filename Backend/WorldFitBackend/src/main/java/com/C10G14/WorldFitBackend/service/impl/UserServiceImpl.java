package com.C10G14.WorldFitBackend.service.impl;

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
import com.C10G14.WorldFitBackend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        if (userDto.getAge() != null) {
           user.setAge(userDto.getAge());
        }
        if (userDto.getSex() != null && !userDto.getSex().isEmpty()) {
            if (userDto.getSex().equals("male"))
                user.setSex(ESex.MALE);
            else if (userDto.getSex().equals("female"))
                user.setSex(ESex.FEMALE);
            else throw new InputNotValidException("Invalid gender, must be male or female");
        }
        if (userDto.getWeight() != null) {
            user.setWeight(userDto.getWeight());
        }
        if (userDto.getHeight() != null) {
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

        ERole roleName = Role.RoletoERole(requestRole);
        if (roleName == null) {
            throw new NotFoundException("Error: Role not found");
        }
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
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
        return mapper.usersToDtoList(userRepository.findByRole(role).orElseThrow(() -> new RuntimeException("Error retrieving role " + requestRole)));
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
