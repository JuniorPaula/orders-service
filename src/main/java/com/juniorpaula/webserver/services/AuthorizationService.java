package com.juniorpaula.webserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.juniorpaula.webserver.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDetails user = repository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("Email not found");
    }
    return user;
  }
}
