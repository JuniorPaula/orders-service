package com.juniorpaula.webserver.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class StandardErro implements Serializable {

  private static final long serialVersionUID = 1L;

  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;

  private final List<FieldMessage> errors = new ArrayList<>();

  public StandardErro() {
  }

  public StandardErro(Instant timestamp, Integer status, String error, String message, String path) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<FieldMessage> getErrors() {
    return errors;
  }

  public void addError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }
}
