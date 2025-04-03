package com.juniorpaula.webserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorpaula.webserver.entities.Client;
import com.juniorpaula.webserver.repositories.ClientRepository;
import com.juniorpaula.webserver.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
  
  @Autowired
  private ClientRepository repository;

  public List<Client> findAll() {
    return repository.findAll();
  }

  public Client findById(Long id) {
    Optional<Client> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public Client insert(Client obj) {
    return repository.save(obj);
  }

  public Client update(Long id, Client obj) {
    try {
      Client entity = repository.getReferenceById(id);
      entity.setName(obj.getName() != null ? obj.getName() : entity.getName());
      entity.setEmail(obj.getEmail() != null ? obj.getEmail() : entity.getEmail());
      entity.setPhone(obj.getPhone() != null ? obj.getPhone() : entity.getPhone());
      return repository.save(entity);
    }
    catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  public void delete(Long id) {
    findById(id);
    repository.deleteById(id);
  }
}
