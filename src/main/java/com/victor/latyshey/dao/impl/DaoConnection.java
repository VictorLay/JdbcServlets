package com.victor.latyshey.dao.impl;

import java.sql.Connection;

abstract public class DaoConnection {

  protected Connection connection;

  public void setConnection(Connection connection) {
    this.connection = connection;
  }
}
