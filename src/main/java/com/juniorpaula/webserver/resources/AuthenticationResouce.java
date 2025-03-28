package com.juniorpaula.webserver.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorpaula.webserver.dto.AuthenticationDTO;
import com.juniorpaula.webserver.dto.RegisterDTO;
import com.juniorpaula.webserver.entities.User;
import com.juniorpaula.webserver.repositories.UserRepository;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResouce {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @PostMapping(value = "/login")
  public ResponseEntity<Void> login(@RequestBody AuthenticationDTO data) {
    var userAuthentication = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = authenticationManager.authenticate(userAuthentication);
    return ResponseEntity.ok().build();
  }

  @PostMapping(value="/register")
  public ResponseEntity<Void> register(@RequestBody RegisterDTO data) {
    if (userRepository.findByEmail(data.email()) != null)
      return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(null, data.name(), data.email(), data.phone(), encryptedPassword);
    userRepository.save(newUser);
    
    return ResponseEntity.ok().build();
  }
}
