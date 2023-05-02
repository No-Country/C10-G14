package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
import com.C10G14.WorldFitBackend.dto.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.UserDto;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById (Long id);

    SimpleUserDto getSimpleUserById (Long id);
    UserDto createUser (UserDto userDto);

    UserDto updateRole(Long id, String requestRole);
    UserDto updateUser (Long id, RegisterRequestDto userDto);
    void deleteUser (Long id);
    List<UserDto> getByRole(String requestRole);
}
