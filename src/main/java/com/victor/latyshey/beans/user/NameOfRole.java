package com.victor.latyshey.beans.user;

import java.util.Optional;
import java.util.stream.Stream;

public enum NameOfRole {

  UNKNOWN_USER("unknown_user",1),
  SIGNED_USER("signed_user", 2),
  EMPLOYEE_USER("employee_user", 3),
  ADMIN_USER("admin_user", 4);

  private final String value;
  private final Integer id;

  NameOfRole(String value, Integer id) {
    this.value = value;
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public Integer getId() {
    return id;
  }

  public static Optional<NameOfRole> of(String value) {
    return Stream.of(NameOfRole.values()).filter(r -> r.value.equalsIgnoreCase(value)).findFirst();
  }
}
