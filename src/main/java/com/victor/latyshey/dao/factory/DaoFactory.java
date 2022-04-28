package com.victor.latyshey.dao.factory;

import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.CustomerDAO;
import com.victor.latyshey.dao.impl.BookImpl;
import com.victor.latyshey.dao.impl.CustomerImpl;

public class DaoFactory {

  private static DaoFactory instance;

  private static BookDAO bookDAO;
  private static CustomerDAO customerDAO;

  private DaoFactory() {
    bookDAO = new BookImpl();
    customerDAO = new CustomerImpl();
  }

  public static DaoFactory getInstance() {
    if (instance == null) {
      instance = new DaoFactory();
    }
    return instance;
  }

  public BookDAO getBookDAO() {
    return bookDAO;
  }

  public CustomerDAO getCustomerDAO() {
    return customerDAO;
  }
}
