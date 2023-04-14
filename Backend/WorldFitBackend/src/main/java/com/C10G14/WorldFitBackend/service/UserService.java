package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
import com.C10G14.WorldFitBackend.dto.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers() throws JsonProcessingException;

    UserDto getUserById (Long id) throws JsonProcessingException;

    SimpleUserDto getSimpleUserById (Long id) throws JsonProcessingException;
    UserDto createUser (UserDto userDto) throws JsonProcessingException;

    UserDto updateRole(Long id, String requestRole) throws JsonProcessingException;
    UserDto updateUser (Long id, RegisterRequestDto userDto) throws IOException;
    void deleteUser (Long id);
    List<UserDto> getByRole(String requestRole) throws JsonProcessingException;
}
