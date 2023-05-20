package com.m1guelsb.crudspring.exception;

public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NotFoundException(String id) {
    super("Register not founded with id: " + id);
  }
}
