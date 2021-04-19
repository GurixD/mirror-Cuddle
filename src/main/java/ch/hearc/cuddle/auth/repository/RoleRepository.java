package ch.hearc.cuddle.auth.repository;

import ch.hearc.cuddle.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
