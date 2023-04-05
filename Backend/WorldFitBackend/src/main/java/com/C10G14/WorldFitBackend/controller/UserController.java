package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.UserDto;
import com.C10G14.WorldFitBackend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @GetMapping
    public List<UserDto> getAllUsers() throws JsonProcessingException {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @GetMapping("/{role}")
    public List<UserDto> getByRole(@PathVariable String role) throws JsonProcessingException {
        return userService.getByRole(role);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) throws JsonProcessingException {
        UserDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) throws JsonProcessingException {
        UserDto newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @GetMapping("/routine/{userId}")
    public ResponseEntity<SimpleUserDto> getUserRoutines(@PathVariable Long userId) throws JsonProcessingException{
        SimpleUserDto user = userService.getSimpleUserById(userId);
        return ResponseEntity.ok(user);
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @PostMapping("/routine/{userId}/{routineId}")
    public ResponseEntity<UserDto> addRoutine(@PathVariable Long userId, @PathVariable Long routineId) throws JsonProcessingException {
        UserDto updatedUser = userService.addRoutine(userId, routineId);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("#userId == authentication.principal.id")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto user) throws JsonProcessingException {
        UserDto updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/role/{userId}/{role}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @PathVariable String role) throws JsonProcessingException {
        UserDto updatedUser = userService.updateRole(userId, role);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @DeleteMapping("/role/{userId}/{role}")
    public ResponseEntity<UserDto> deleteRoutine(@PathVariable Long userId, @PathVariable Long routineId) throws JsonProcessingException {
        UserDto updatedUser = userService.deleteRoutine(userId, routineId);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

