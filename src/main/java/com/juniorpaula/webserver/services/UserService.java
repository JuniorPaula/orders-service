package com.juniorpaula.webserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.juniorpaula.webserver.entities.User;
import com.juniorpaula.webserver.entities.enums.UserRole;
import com.juniorpaula.webserver.repositories.UserRepository;
import com.juniorpaula.webserver.security.SecurityUtils;
import com.juniorpaula.webserver.services.exceptions.DatabaseException;
import com.juniorpaula.webserver.services.exceptions.ForbidenException;
import com.juniorpaula.webserver.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
  
  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    Optional<User> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public void delete(Long id) {
    try {
      findById(id);
      repository.deleteById(id);
    }
    catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  public User update(Long id, User obj) {
    try {
      validationPermission(id);

      User entity = repository.getReferenceById(id);
      updateData(entity, obj);
      return repository.save(entity);
    }
    catch(EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  private void validationPermission(Long id) {
    User loggedUser = SecurityUtils.getCurrentUser();
    if (!loggedUser.getId().equals(id) && !UserRole.ADMIN.equals(loggedUser.getRole())) {
      throw new ForbidenException();
    }
  }

  private void updateData(User entity, User obj) {
    entity.setName(obj.getName() != null ? obj.getName() : entity.getName());
    entity.setEmail(obj.getEmail() != null ? obj.getEmail() : entity.getEmail());
    entity.setPhone(obj.getPhone() != null ? obj.getPhone() : entity.getPhone());
    entity.setRole(obj.getRole() != null ? obj.getRole() : entity.getRole());
    entity.setPassword(obj.getPassword() != null ? updatePassword(obj.getPassword()) : entity.getPassword());
  }

  private String updatePassword(String newPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(newPassword);
  }
}
