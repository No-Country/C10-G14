package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.RegisterRequestDto;
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

import java.io.IOException;
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH')")
    @GetMapping("/role/{role}")
    public List<UserDto> getByRole(@PathVariable String role) throws JsonProcessingException {
        return userService.getByRole(role);
    }

    @Operation(summary = "Get user complete information by Id")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH')")
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
            @ApiResponse(responseCode = "400", description = """
                      Possible Responses:
                     
                    - Email is required
                    - Email direction isn't valid
                    - Password is required
                    - Password must contain at least 8 characters including letters, numbers, spaces and commas
                    - Weight must be greater than or equal to zero
                    - Weight must have two or fewer decimal places
                    - Height must be greater than or equal to zero
                    - Height must have two or fewer decimal places
                    - Sex must be either male or female (no case sensitive)
                    - Maximum age is 110
                    """,
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
            @ApiResponse(responseCode = "400", description = """
                      Possible Responses:
                     
                    - Weight must be greater than or equal to zero
                    - Weight must have two or fewer decimal places
                    - Height must be greater than or equal to zero
                    - Height must have two or fewer decimal places
                    - Sex must be either male or female (no case sensitive)
                    - Maximum age is 110
                    """,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: An email is required",
                    content = @Content)})
    @PreAuthorize("#userId == authentication.principal.id")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId,
                                              @ModelAttribute RegisterRequestDto user) throws IOException {
        UserDto updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Update the role of an user")
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
    @PreAuthorize("hasRole('ADMIN') or (hasRole('PROFESSOR') and (#role == 'customer' or #role == 'user'))")
    @PutMapping("/role/{userId}/{role}")
    public ResponseEntity<UserDto> updateRole(@PathVariable Long userId, @PathVariable String role) throws JsonProcessingException {
        UserDto updatedUser = userService.updateRole(userId, role);
        return ResponseEntity.ok(updatedUser);
    }


    @Operation(summary = "Delete an user")
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

    @Operation(summary = "Get user and its routines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleUserDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: User not found",
                    content = @Content),})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH') or #userId == authentication.principal.id")
    @GetMapping("/routine/{userId}")
    public ResponseEntity<SimpleUserDto> getUserRoutines(@PathVariable Long userId) throws JsonProcessingException{
        SimpleUserDto user = userService.getSimpleUserById(userId);
        return ResponseEntity.ok(user);
    }
}

