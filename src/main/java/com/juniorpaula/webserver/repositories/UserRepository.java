package com.juniorpaula.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorpaula.webserver.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
