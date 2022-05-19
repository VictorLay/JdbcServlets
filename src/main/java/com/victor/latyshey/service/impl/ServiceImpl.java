package com.victor.latyshey.service.impl;

import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.Transaction;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.Service;
import com.victor.latyshey.service.exception.ServiceException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceImpl implements Service {

  private static final Logger logger = LogManager.getLogger(ServiceImpl.class);

  protected Transaction transaction;

  public ServiceImpl(Transaction transaction) {
    this.transaction = transaction;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  @Override
  public void releaseTheConnection()  {
    try {
      transaction.getConnection().close();
    } catch (SQLException e) {
      logger.log(Level.ERROR, "The connection resource isn't been closed", e);
    }

  }
}
