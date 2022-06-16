package com.victor.latyshey.bean.user;

import java.util.Optional;
import java.util.stream.Stream;

public enum Role {

  UNKNOWN_USER(1),
  SIGNED_USER(2),
  EMPLOYEE_USER(3),
  ADMIN_USER(4);

  private final Integer id;

  Role(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public static Optional<Role> of(String value) {
    return Stream.of(Role.values()).filter(r -> r.toString().equalsIgnoreCase(value)).findFirst();
  }

  public static boolean isEqual(Role role, String value) {
    Optional<Role> optionalRole = Stream.of(Role.values())
        .filter(r -> r.toString().equalsIgnoreCase(value)).findFirst();
    return optionalRole.filter(role::equals).isPresent();
  }


}
