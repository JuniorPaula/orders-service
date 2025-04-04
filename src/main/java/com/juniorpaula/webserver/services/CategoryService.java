package com.juniorpaula.webserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorpaula.webserver.entities.Category;
import com.juniorpaula.webserver.repositories.CategoryRepository;
import com.juniorpaula.webserver.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository repository;

  public List<Category> findAll() {
    return repository.findAll();
  }

  public Category findById(Long id) {
    Optional<Category> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public Category insert(Category obj) {
    return repository.save(obj);
  }

  public Category update(Long id, Category obj) {
    try {
      Category entity = repository.getReferenceById(id);
      entity.setName(obj.getName() != null ? obj.getName() : entity.getName());
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
