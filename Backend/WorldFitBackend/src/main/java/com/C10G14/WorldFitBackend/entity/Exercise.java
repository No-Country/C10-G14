package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UNIT_ID")
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Column(name = "MEDIA")
    private String media;

}
