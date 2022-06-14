package com.victor.latyshey.dao.transaction;

import com.victor.latyshey.dao.AuthorDao;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.UserDAO;
import com.victor.latyshey.dao.exception.DaoException;
import java.io.Closeable;
import java.sql.Connection;

public interface Transaction extends Closeable {

  AuthorDao getAuthorDao();

  BookDAO getBookDao();

  UserDAO getUserDao();

  void commit() throws DaoException;

  void rollback() throws DaoException;

  Connection getConnection();
}
