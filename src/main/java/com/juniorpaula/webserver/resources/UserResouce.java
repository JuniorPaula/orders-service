package com.juniorpaula.webserver.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorpaula.webserver.entities.User;
import com.juniorpaula.webserver.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResouce {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {

    List<User> users = service.findAll();

    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {

    User obj = service.findById(id);

    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    service.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {

    obj = service.update(id, obj);

    return ResponseEntity.ok().body(obj);
  }
}
