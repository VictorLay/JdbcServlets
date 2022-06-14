package com.victor.latyshey.dao.factory;

import com.victor.latyshey.dao.AuthorDao;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.UserDAO;
import com.victor.latyshey.dao.impl.AuthorDaoImpl;
import com.victor.latyshey.dao.impl.BookDAOImpl;
import com.victor.latyshey.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FactoryDAO {

  private static final String FACTORY_CREATE_DEBUG_LOG =
      "The new factory object of class " + FactoryDAO.class + " was created. hash = ";
  private static final Logger logger = LogManager.getLogger(FactoryDAO.class);
  private static FactoryDAO instance;


  private final UserDAO userDAO;
  private final AuthorDao authorDao;
  private final BookDAO bookDAO;

  FactoryDAO(UserDAO userDAO, AuthorDao authorDao, BookDAO bookDAO) {
    this.userDAO = userDAO;
    this.authorDao = authorDao;
    this.bookDAO = bookDAO;
    instance = this;
  }

  private FactoryDAO() {
    userDAO = new UserDaoImpl();
    authorDao = new AuthorDaoImpl();
    bookDAO = new BookDAOImpl();

    logger.log(Level.DEBUG, FACTORY_CREATE_DEBUG_LOG);
    logger.log(Level.DEBUG, this.hashCode());
  }


  public static FactoryDAO getInstance() {
    if (instance == null) {
      instance = new FactoryDAO();
    }
    return instance;
  }


  public UserDAO getUserDAO() {
    return userDAO;
  }

  public AuthorDao getAuthorDao() {
    return authorDao;
  }

  public BookDAO getBookDAO() {
    return bookDAO;
  }
}
