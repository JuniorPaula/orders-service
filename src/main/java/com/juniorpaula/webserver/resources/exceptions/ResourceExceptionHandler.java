package com.juniorpaula.webserver.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.juniorpaula.webserver.services.exceptions.DatabaseException;
import com.juniorpaula.webserver.services.exceptions.ForbidenException;
import com.juniorpaula.webserver.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
  
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardErro> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    String error = "Resource not found";
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardErro err = new StandardErro(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardErro> database(DatabaseException e, HttpServletRequest request) {
    String error = "Database error";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardErro err = new StandardErro(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(ForbidenException.class)
  public ResponseEntity<StandardErro> forbiden(ForbidenException e, HttpServletRequest request) {
    String error = "Forbiden";
    HttpStatus status = HttpStatus.FORBIDDEN;
    StandardErro err = new StandardErro(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardErro> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
    String error = "Validation error";
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    StandardErro err = new StandardErro(Instant.now(), status.value(), error, "Input request body fail", request.getRequestURI());

    for (FieldError f : e.getBindingResult().getFieldErrors()) {
      err.addError(f.getField(), f.getDefaultMessage());
    }

    return ResponseEntity.status(status).body(err);
  }
}
