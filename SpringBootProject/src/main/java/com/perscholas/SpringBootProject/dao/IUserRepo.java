package com.perscholas.SpringBootProject.dao;

import java.util.Optional;

import com.perscholas.SpringBootProject.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	User findByEmail(String email);
}
