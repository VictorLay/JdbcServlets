package com.victor.latyshey.dao.impl;

import java.sql.Connection;

public abstract class DaoConnection {

  protected ThreadLocal<Connection> connectionThreadLocal;

  public void setConnection(Connection connection) {
    this.connectionThreadLocal = ThreadLocal.withInitial(() -> connection);
  }
}
