package com.juniorpaula.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorpaula.webserver.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
