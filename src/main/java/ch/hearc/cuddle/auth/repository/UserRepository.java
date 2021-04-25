package ch.hearc.cuddle.auth.repository;

import ch.hearc.cuddle.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
