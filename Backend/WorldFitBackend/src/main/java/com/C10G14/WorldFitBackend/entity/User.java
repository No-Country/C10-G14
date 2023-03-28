package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "USERS")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@NotBlank
    @Column(name = "NAME")
    private String name;

    @Column(name = "PROFILE_IMAGE")
    private String profileImg;

    @Column
    private String email;





}
