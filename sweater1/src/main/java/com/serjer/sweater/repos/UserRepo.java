package com.serjer.sweater.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serjer.sweater.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
