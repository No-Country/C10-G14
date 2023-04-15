package com.C10G14.WorldFitBackend.entity;

import com.C10G14.WorldFitBackend.enumeration.ERole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME"
            ,unique = true)
    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public static ERole RoletoERole (String role) {
        return (role.equals("user")? ERole.ROLE_USER :
                (role.equals("couch")? ERole.ROLE_COUCH :
                        (role.equals("customer")? ERole.ROLE_CUSTOMER :
                                (role.equals("admin")? ERole.ROLE_ADMIN :
                                        null)
                        )
                )
        );
    }
}
