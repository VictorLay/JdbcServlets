package com.victor.latyshey.beans.user;

import static com.victor.latyshey.beans.user.NameOfRole.of;

import com.victor.latyshey.beans.Entity;
import java.io.Serializable;

public class Role extends Entity implements Serializable {
  private static final long serialVersionUID = 42L;


  private NameOfRole nameOfRole;

  public Role() {
    this.nameOfRole = NameOfRole.UNKNOWN_USER;
    super.setId(1);
  }

  public Role(NameOfRole nameOfRole) {
    this.nameOfRole = nameOfRole;
    super.setId(nameOfRole.getId());
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
