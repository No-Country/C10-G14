package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Registration_Tokens")
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long Id;
    @Column(name = "TOKEN", nullable = false)
    String token;
    @Column(name = "CREATED_AT", nullable = false)
    ZonedDateTime createdAt;
    @Column(name = "EXPIRES_AT",  nullable = false)
    ZonedDateTime expiresAt;
    @Column(name = "CONFIRMED_AT")
    ZonedDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false,
            name = "USER_ID")
    User user;

    @PrePersist
    protected void onCreate() {
        this.createdAt = ZonedDateTime.now(ZoneId.of("GMT-3"));
        this.expiresAt = ZonedDateTime.now(ZoneId.of("GMT-3")).plusMinutes(15);
    }
}
