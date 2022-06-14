package com.victor.latyshey.dao.factory;

import com.victor.latyshey.dao.AuthorDao;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.UserDAO;

public class DaoFactoryTestsInitializer {

  public static void initialize(UserDAO userDAO, AuthorDao authorDao, BookDAO bookDAO) {
    new FactoryDAO(userDAO, authorDao, bookDAO);
  }

}
