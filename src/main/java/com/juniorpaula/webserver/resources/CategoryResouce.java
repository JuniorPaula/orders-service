package com.juniorpaula.webserver.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorpaula.webserver.entities.Category;
import com.juniorpaula.webserver.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResouce {

  @Autowired
  private CategoryService service;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {

    List<Category> categories = service.findAll();

    return ResponseEntity.ok().body(categories);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Category> findById(@PathVariable Long id) {

    Category obj = service.findById(id);

    return ResponseEntity.ok().body(obj);
  }
}
