package com.juniorpaula.webserver.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.juniorpaula.webserver.entities.User;

@Service
public class TokenService {

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
        .withIssuer("webserver")
        .withSubject(user.getEmail())
        .withExpiresAt(getExpirationDate())
        .sign(algorithm);
      return token;
    } 
    catch (JWTCreationException e) {
      throw new RuntimeException("Error creating token", e);
    }
  }

  private Instant getExpirationDate() {
    return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWT.require(algorithm).withIssuer("webserver").build().verify(token);
      return JWT.decode(token).getSubject();
    } 
    catch (JWTVerificationException e) {
      return "";
    }
  }
}
