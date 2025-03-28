package com.juniorpaula.webserver.services.exceptions;

public class ForbidenException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ForbidenException() {
    super("Access denied");
  }
}
