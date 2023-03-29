package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Table(name = "Users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@NotBlank
    @Column(name = "NAME")
    private String name;

    @Column(name = "PROFILE_IMAGE")
    private String profileImg;

    @Column(name = "CLIENT_SINCE")
    private String clientSince;

    @PrePersist
    protected void onCreate() {
        this.clientSince = ZonedDateTime.now(ZoneId.of("GMT-3")).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    public User(String name, String profileImg) {
        this.name = name;
        this.profileImg = profileImg;
    }
}
