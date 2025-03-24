package com.juniorpaula.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorpaula.webserver.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
