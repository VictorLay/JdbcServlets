package com.victor.latyshey.dao.impl;

import java.sql.Connection;

public abstract class DaoConnection {

  protected ThreadLocal<Connection> connection;

  public void setConnection(Connection connection) {
    this.connection = ThreadLocal.withInitial(() -> connection);
  }
}
