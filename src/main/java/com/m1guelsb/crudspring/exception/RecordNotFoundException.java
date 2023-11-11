package com.m1guelsb.crudspring.exception;

public class RecordNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public RecordNotFoundException(String id) {
    super("Register not founded with id: " + id);
  }
}
