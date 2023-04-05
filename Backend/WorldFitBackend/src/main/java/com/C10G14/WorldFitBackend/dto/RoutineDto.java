package com.C10G14.WorldFitBackend.dto;

import java.util.Set;

public class RoutineDto {
    private Long id;

    private String title;
    private String name;
    private Set<Exercise_RoutineDto> exercise_routines;

    // Constructor sin argumentos
    public RoutineDto(long id, String rutina1, String s) {
    }

    // Constructor con argumentos
    public RoutineDto(Long id, String name, Set<Exercise_RoutineDto> exercise_routines) {
        this.id = id;
        this.name = name;
        this.exercise_routines = exercise_routines;
    }

    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Exercise_RoutineDto> getExercise_routines() {
        return exercise_routines;
    }

    public void setExercise_routines(Set<Exercise_RoutineDto> exercise_routines) {
        this.exercise_routines = exercise_routines;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


