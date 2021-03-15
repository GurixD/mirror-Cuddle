package com.hearc.cuddle.repositories;

import com.hearc.cuddle.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
		
}
