package com.C10G14.WorldFitBackend.controller;


import com.C10G14.WorldFitBackend.dto.*;
import com.C10G14.WorldFitBackend.service.RoutineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @Operation(summary = "Create a new routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new routine",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = """
                      Possible Responses:
                     
                    - Title is required
                    - Media Url not valid
                    """,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: User not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: The User already has routine with that name",
            content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @PostMapping
    public ResponseEntity<RoutineResponseDto> createRoutine(@RequestBody @Valid RoutineRequestDto routine) {
        RoutineResponseDto createdRoutine = routineService.createRoutine(routine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutine);
    }

    @Operation(summary = "Get all routines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of routines or a empty list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @GetMapping
    public ResponseEntity<List<RoutineResponseDto>> getAllRoutines() {
        List<RoutineResponseDto> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
    }

    @Operation(summary = "Get a routine by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A routine",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = "Routine Id is required",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Routine don't exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH') or #userId == authentication.principal.id")
    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponseDto> getRoutineById(@PathVariable Long id) {
        RoutineResponseDto routine = routineService.getRoutineById(id);
        return ResponseEntity.ok(routine);
    }

    @Operation(summary = "Update an existing routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A updated routine",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = """
                      Possible Responses:
                     
                    - Title is required
                    - Media Url not valid
                    """,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Routine don't exist",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: The User already has routine with that name",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @PutMapping("/{id}")
    public ResponseEntity<RoutineResponseDto> updateRoutine(@PathVariable Long id,
                                                            @RequestBody @Valid RoutineRequestDto routine) {
        RoutineResponseDto updatedRoutine = routineService.updateRoutine(id, routine);
        return ResponseEntity.ok(updatedRoutine);
    }

    @Operation(summary = "Delete a routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Routine don't exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return new ResponseEntity<>("Routine successfully deleted",HttpStatus.OK);
    }

    @Operation(summary = "Add a exercise to a routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A routine with the new exercise added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Exercise is required",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @PostMapping("/exercises/{id}")
    public ResponseEntity<RoutineResponseDto> addExercise(@PathVariable("id") long routineId,
                                                  @RequestBody @Valid Exercise_RoutineRequestDto exercise) {
        RoutineResponseDto updatedRoutine = routineService.addExercise(routineId,exercise);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    @Operation(summary = "Update an exercise in an existing routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A routine with a updated exercise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Exercise is required",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @PutMapping("/exercises/{id}")
    public ResponseEntity<RoutineResponseDto> updateExercise(@PathVariable("id") long routineId,
                                                  @RequestBody @Valid Exercise_RoutineRequestDto exercise) {
        RoutineResponseDto updatedRoutine = routineService.updateExercise(routineId,exercise);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    @Operation(summary = "Remove an exercise from an existing routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A routine without the removed exercise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: exercise does not exist in this routine",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COUCH')")
    @DeleteMapping("/exercises/{id}")
    public ResponseEntity<MessageResponse> removeExercise(@PathVariable("id") long routineId,
                                                          @RequestBody RemoveExerciseRequestDto exercises) {
        RoutineResponseDto updatedRoutine = routineService.removeExercise(routineId,exercises);
        return new ResponseEntity<>(new MessageResponse(200,"Routine deleted"),
                HttpStatus.OK);
    }
}
