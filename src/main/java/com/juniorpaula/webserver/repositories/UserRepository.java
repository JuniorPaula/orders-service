package com.juniorpaula.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.juniorpaula.webserver.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  UserDetails findByEmail(String email);
}
