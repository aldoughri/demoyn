package com.example.demo.configuration.Securty;

import java.util.Optional;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	Optional<User> findByUsername(String userName);
	

}
