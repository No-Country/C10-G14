package com.C10G14.WorldFitBackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User  implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PROFILE_IMAGE")
    private String profileImg;

    @Column(name = "CLIENT_SINCE")
    private String clientSince;

    @Column(name = "WEIGHT")
    private String weight;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "AGE")
    private int age;

    @JsonBackReference
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Routine> routines;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    public void addRoutine(Routine routine) {
        routines.add(routine);
        routine.setUser(this);
    }

    public void removeRoutine(Routine routine) {
        routines.remove(routine);
        routine.setUser(null);
    }

    @PrePersist
    protected void onCreate() {
        this.clientSince = ZonedDateTime.now(ZoneId.of("GMT-3")).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = role;
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return authorities;
    }

    // USER DETAILS
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
