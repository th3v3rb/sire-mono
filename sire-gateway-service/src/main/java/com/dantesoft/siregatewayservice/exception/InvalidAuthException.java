package com.dantesoft.siregatewayservice.exception;

public class InvalidAuthException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InvalidAuthException(String message) {
    super(message);
  }
}
