package com.victor.latyshey.beans;

import static com.victor.latyshey.beans.NameOfRole.of;

public class Role extends Entity {

  private NameOfRole nameOfRole;

  public Role() {
    this.nameOfRole = NameOfRole.UNKNOWN_USER;
    super.setId(1);
  }

  public Role(String role, Integer id) {
    this.nameOfRole = of(role).isPresent() ? of(role).get() : NameOfRole.UNKNOWN_USER;
    super.setId(id);
  }

  public NameOfRole getRoleName() {
    return nameOfRole;
  }

  public void setRoleName(NameOfRole nameOfRole) {
    this.nameOfRole = nameOfRole;
  }

  @Override
  public String toString() {
    return "nameOfRole=" + nameOfRole.toString() + " roleId=" + super.getId();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode() + nameOfRole.hashCode();
  }
}
