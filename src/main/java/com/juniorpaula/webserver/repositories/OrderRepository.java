package com.juniorpaula.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorpaula.webserver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
