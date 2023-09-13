package com.app.blog.repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.User;
public interface UserRepo extends JpaRepository<User, Integer> {

	
Optional<User>findByEmail(String email);
}
