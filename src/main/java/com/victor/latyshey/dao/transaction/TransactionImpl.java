package com.victor.latyshey.dao.transaction;

import com.victor.latyshey.dao.AuthorDao;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.DAO;
import com.victor.latyshey.dao.UserDAO;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.factory.FactoryDAO;
import com.victor.latyshey.dao.impl.AuthorDaoImpl;
import com.victor.latyshey.dao.impl.BookDAOImpl;
import com.victor.latyshey.dao.impl.DaoConnection;
import com.victor.latyshey.dao.impl.UserDaoImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionImpl implements Transaction {

  private final Logger logger = LogManager.getLogger(TransactionImpl.class);
  private static final String COMMIT_ERROR_LOG = "Transaction: Commit error!";
  private static final String ROLLBACK_ERROR_LOG = "Transaction: rollback error!";

  private Connection connection;

  public TransactionImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Connection getConnection() {
    return connection;
  }

  @Override
  public AuthorDao getAuthorDao() {
    AuthorDao authorDao = FactoryDAO.getInstance().getAuthorDao();
//    AuthorDao authorDao = new AuthorDaoImpl();
    return setConnection(authorDao);
  }

  @Override
  public BookDAO getBookDao() {
    BookDAO bookDAO = FactoryDAO.getInstance().getBookDAO();
//    BookDAO bookDAO = new BookDAOImpl();
    return setConnection(bookDAO);
  }

  @Override
  public UserDAO getUserDao() {
    UserDAO userDAO = FactoryDAO.getInstance().getUserDAO();
    return setConnection(userDAO);
  }

  private <A extends DAO> A setConnection(A dao) {
    ((DaoConnection) dao).setConnection(connection);
    return dao;
  }

  /**
   * After closing transaction happen rollback for this transaction and uncommitted changes will
   * lose.
   *
   * @throws DaoException
   */
  @Override
  public void commit() throws DaoException {
    try {
      connection.commit();
    } catch (SQLException e) {
      logger.log(Level.ERROR, COMMIT_ERROR_LOG, e);
      throw new DaoException(e);
    }
  }

  @Override
  public void rollback() throws DaoException {
    try {
      connection.rollback();
    } catch (SQLException e) {
      logger.log(Level.ERROR, ROLLBACK_ERROR_LOG, e);
      throw new DaoException(e);
    }
  }

  @Override
  public void close() throws IOException {
    try {
      connection.rollback();
    } catch (SQLException e) {
      logger.log(Level.ERROR, "The rollback operation had completed unsuccessfully", e);
      throw new IOException(e);
    }
    try {
      connection.close();
    } catch (SQLException e) {
      logger.log(Level.ERROR, "The connection resource isn't been closed", e);
      throw new IOException(e);
    }

  }

}
