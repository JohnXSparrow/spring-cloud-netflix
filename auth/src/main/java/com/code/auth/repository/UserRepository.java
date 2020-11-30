package com.code.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.code.auth.entity.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(name = "SELECT u FROM User u WHERE u.username=:username")
	User findByUsername(@Param("username") String username);

}
