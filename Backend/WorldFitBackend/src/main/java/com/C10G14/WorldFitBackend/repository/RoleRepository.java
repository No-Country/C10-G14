package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
