package com.victor.latyshey.beans;

import java.util.Optional;
import java.util.stream.Stream;

public enum NameOfRole {

  UNKNOWN_USER("unknown_user"),
  SIGNED_USER("signed_user"),
  EMPLOYEE_USER("employee_user"),
  ADMIN_USER("admin_user");

  private final String value;

  NameOfRole(String value) {
    this.value = value;
  }

  public static Optional<NameOfRole> of(String value) {
    return Stream.of(NameOfRole.values()).filter(r -> r.value.equalsIgnoreCase(value)).findFirst();
  }
}
