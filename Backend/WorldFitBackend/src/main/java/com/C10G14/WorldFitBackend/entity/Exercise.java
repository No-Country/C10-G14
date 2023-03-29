package com.C10G14.WorldFitBackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MEDIA")
    private String media;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EXERCISE_UNIT")
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @JsonBackReference
    @OneToMany(
            mappedBy = "exercise",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<Exercise_Routine> routines;

    public Exercise(Long id, String title, String media, Unit unit) {
        this.id = id;
        this.title = title;
        this.media = media;
        this.unit = unit;
        this.routines = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", media='" + media + '\'' +
                ", unit=" + unit +
                '}';
    }
}
