package com.C10G14.WorldFitBackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "Routines")
@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE", length = 100)
    private String title;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "routine",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<Exercise_Routine> exercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "ID", nullable = false)
    private User user;

    public Routine(String title, User user) {
        this.title = title;
        this.user = user;
        this.exercises = new LinkedHashSet<>();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addExercise(Exercise exercise, int quantity, int repetitions, int series){
        Exercise_Routine exercise_routine = new Exercise_Routine(exercise,this,quantity,series,repetitions);
        exercises.add(exercise_routine);
        exercise.getRoutines().add(exercise_routine);
    }

    public void removeExercise(Exercise exercise) {
        exercises.removeIf(e -> e.getRoutine().equals(this) && e.getExercise().equals(exercise));
    }

    public void updateExercise(Exercise exercise, int quantity,int repetitions, int series) {
        exercises.stream()
                .filter(e -> e.getRoutine().equals(this)
                        && e.getExercise().equals(exercise))
                .findFirst()
                .ifPresent(e -> {e.setRepetitions(repetitions);
                e.setSeries(series);
                e.setQuantity(quantity);});
    }
}





