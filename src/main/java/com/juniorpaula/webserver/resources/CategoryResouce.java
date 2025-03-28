package com.juniorpaula.webserver.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

  @PostMapping
  public ResponseEntity<Category> insert(@RequestBody Category obj) {

    obj = service.insert(obj);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(location).body(obj);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category obj) {

    Category c = service.update(id, obj);

    return ResponseEntity.ok().body(c);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
