package com.hearc.cuddle;

import com.hearc.cuddle.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
		
}
