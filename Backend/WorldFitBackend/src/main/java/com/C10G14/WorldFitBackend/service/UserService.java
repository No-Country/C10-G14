package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.ExerciseDto;
import com.C10G14.WorldFitBackend.dto.UserDto;
import com.C10G14.WorldFitBackend.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById (Long id);

    UserDto createUser (UserDto userDto);

    UserDto updateUser (Long id, UserDto userDto);
    void deleteUser (Long id);
}
