package com.juniorpaula.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorpaula.webserver.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
