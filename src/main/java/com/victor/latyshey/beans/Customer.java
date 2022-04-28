package com.victor.latyshey.beans;

public class Customer {

  private String name;
  private String lastname;
  private String login;
  private String password;

  private int id;

  public Customer(){}


  public Customer(String name, String lastname, String login, String password) {
    this.name = name;
    this.lastname = lastname;
    this.login = login;
    this.password = password;
  }
//todo рефакторинг класса. убрать ненужные поля такие как пароль.
  public Customer(String name, String lastname, String login, int id) {
    this.name = name;
    this.lastname = lastname;
    this.login = login;
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getLogin() {
    return login;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "name='" + name + '\'' +
        ", lastname='" + lastname + '\'' +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", id=" + id +
        '}';
  }
}
