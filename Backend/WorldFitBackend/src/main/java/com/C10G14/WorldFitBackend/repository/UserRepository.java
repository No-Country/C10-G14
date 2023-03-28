package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
