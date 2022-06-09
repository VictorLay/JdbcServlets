package com.victor.latyshey.bean;

import java.io.Serializable;

public class UserSessionInf implements Serializable {

  private final String login;
  private final String role;
  private final Integer id;

  public UserSessionInf(String login, String role, Integer id) {
    this.login = login;
    this.role = role;
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public String getRole() {
    return role;
  }

  public Integer getId() {
    return id;
  }
}
