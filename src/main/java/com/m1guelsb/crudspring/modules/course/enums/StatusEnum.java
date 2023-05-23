package com.m1guelsb.crudspring.modules.course.enums;

public enum StatusEnum {
  ENABLED("ENABLED"), DISABLED("DISABLED");

  private String value;

  private StatusEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
