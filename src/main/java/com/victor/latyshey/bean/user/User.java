package com.victor.latyshey.bean.user;

import com.victor.latyshey.bean.Entity;
import java.util.Objects;

public class User extends Entity {

  private String login;
  private String password;
  private Role role;

  public User(String login) {
    this.login = login;
  }

  public User(String login, String password) {
    this.login = login;
    this.password = password;
    this.role = Role.UNKNOWN_USER;
  }

  public User(String login, String password, Role role) {
    this.login = login;
    this.password = password;
    this.role = role;
  }

  public User(String login, Role role, Integer id) {
    this.login = login;
    this.role = role;
    super.setId(id);
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(login, user.login) && Objects.equals(password, user.password)
        && Objects.equals(role, user.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), login, password, role);
  }

  @Override
  public String toString() {
    return "User [login=" + login + ", password=" + password + ", role=" + role + ']';
  }
}
