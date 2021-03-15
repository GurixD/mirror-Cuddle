package com.hearc.cuddle.auth.repository;

import com.hearc.cuddle.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
