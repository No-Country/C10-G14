package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.SimpleUserDto;
import com.C10G14.WorldFitBackend.dto.UserDto;
import com.C10G14.WorldFitBackend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of all users or a empty list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @GetMapping
    public List<UserDto> getAllUsers() throws JsonProcessingException {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get a list of users by role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of users or a empty list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Role does not exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @GetMapping("/{role}")
    public List<UserDto> getByRole(@PathVariable String role) throws JsonProcessingException {
        return userService.getByRole(role);
    }

    @Operation(summary = "Get one user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: an id is required",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: User does not exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) throws JsonProcessingException {
        UserDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Email is required",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: Password is required",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) throws JsonProcessingException {
        UserDto newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Operation(summary = "Update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "An updated user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: An email is required",
                    content = @Content)})
    @PreAuthorize("#userId == authentication.principal.id")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto user) throws JsonProcessingException {
        UserDto updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Update the role of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A user with updated roles",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Role not found",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/role/{userId}/{role}")
    public ResponseEntity<UserDto> updateRole(@PathVariable Long userId, @PathVariable String role) throws JsonProcessingException {
        UserDto updatedUser = userService.updateRole(userId, role);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete a routine from a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A user with a deleted routine",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Routine does not exist in this user",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @DeleteMapping("/{userId}/routine/{routine}")
    public ResponseEntity<UserDto> deleteRoutine(@PathVariable Long userId, @PathVariable Long routineId) throws JsonProcessingException {
        UserDto updatedUser = userService.deleteRoutine(userId, routineId);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: User not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

