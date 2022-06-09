package com.victor.latyshey.dao.transaction;

import com.victor.latyshey.dao.connection.ConnectionPool;
import com.victor.latyshey.dao.connection.PoolException;
import com.victor.latyshey.dao.exception.DaoException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionFactory {

  private static final String FACTORY_CREATE_LOG = "The new factory object was created. hash = ";

  private final Logger logger = LogManager.getLogger(TransactionFactory.class);

  private static TransactionFactory instance;

  private TransactionFactory() {
    logger.debug(FACTORY_CREATE_LOG);
    logger.debug(this.hashCode());
  }

  public static TransactionFactory getInstance() throws DaoException {
    if (instance == null) {
      instance = new TransactionFactory();
    }
    return instance;
  }

  public Transaction getTransaction() throws DaoException {

    try {
      Connection connection = ConnectionPool.getInstance().getConnection();
      connection.setAutoCommit(false);
      return new TransactionImpl(connection);
    } catch (SQLException | PoolException e) {
      throw new DaoException();
    }
  }


}
