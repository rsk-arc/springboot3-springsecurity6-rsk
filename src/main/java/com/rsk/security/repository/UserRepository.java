package com.rsk.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rsk.security.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	// Since email is unique, we'll find users by email
	Optional<User> findByEmail(String email);

	@Query("update User u set u.password = :password WHERE u.id = :userId")
	void setPassword(@Param("userId") Long id, @Param("password") String password);
}
