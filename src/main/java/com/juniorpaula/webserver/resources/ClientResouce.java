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

import com.juniorpaula.webserver.entities.Client;
import com.juniorpaula.webserver.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResouce {

  @Autowired
  private ClientService service;

  @GetMapping
  public ResponseEntity<List<Client>> findAll() {

    List<Client> obj = service.findAll();

    return ResponseEntity.ok().body(obj);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Client> findById(@PathVariable Long id) {

    Client obj = service.findById(id);

    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  public ResponseEntity<Client> insert(@RequestBody Client obj) {

    obj = service.insert(obj);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(location).body(obj);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client obj) {

    Client c = service.update(id, obj);

    return ResponseEntity.ok().body(c);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
