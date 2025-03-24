package com.juniorpaula.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorpaula.webserver.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
