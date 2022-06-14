package com.victor.latyshey.dao.transaction;

import com.victor.latyshey.dao.connection.ConnectionPool;
import com.victor.latyshey.dao.connection.PoolException;
import com.victor.latyshey.dao.exception.DaoException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionFactory {

  private static final String FACTORY_CREATE_DEBUG_LOG = "The new factory object was created. hash = ";
  private static final String GET_TRANSACTION_ERROR_LOG = "Connection initialization was failed! The transaction wasn't got!";

  private final Logger logger = LogManager.getLogger(TransactionFactory.class);

  private static TransactionFactory instance;

  private TransactionFactory() {
    logger.log(Level.DEBUG, FACTORY_CREATE_DEBUG_LOG);
    logger.log(Level.DEBUG, this.hashCode());
  }

  public static TransactionFactory getInstance() {
    if (instance == null) {
      instance = new TransactionFactory();
    }
    return instance;
  }

  /**
   * The getTransaction method return the transaction witch was initialized in this method with free
   * connection from connection pool. Initialization includes disable autocommit mod for used
   * connection.
   *
   * @return
   * @throws DaoException
   */
  public Transaction getTransaction() throws DaoException {

    try {
      Connection connection = ConnectionPool.getInstance().getConnection();
      connection.setAutoCommit(false);
      return new TransactionImpl(connection);
    } catch (SQLException | PoolException e) {
      logger.log(Level.ERROR, GET_TRANSACTION_ERROR_LOG);
      throw new DaoException(e);
    }
  }


}
