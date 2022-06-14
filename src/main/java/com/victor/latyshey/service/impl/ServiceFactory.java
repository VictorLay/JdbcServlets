package com.victor.latyshey.service.impl;

import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.UserService;

public class ServiceFactory {

  private static ServiceFactory instance;
  private final UserService userService;
  private final BookService bookService;

  private ServiceFactory() {
    this.userService = new UserServiceImpl();
    this.bookService = new BookServiceImpl();
  }

  public static ServiceFactory getInstance() {
    if (instance == null) {
      instance = new ServiceFactory();
    }
    return instance;
  }

  public UserService getUserService(){
    return userService;
  }

  public BookService getBookService(){
    return bookService;
  }
}
