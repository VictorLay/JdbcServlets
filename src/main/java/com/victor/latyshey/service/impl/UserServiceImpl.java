package com.victor.latyshey.service.impl;

import com.victor.latyshey.beans.Book;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.factory.DaoFactory;
import com.victor.latyshey.service.UserService;
import java.util.ArrayList;

public class UserServiceImpl implements UserService {

  private static DaoFactory daoFactory = DaoFactory.getInstance();
  private static BookDAO bookDAO = daoFactory.getBookDAO();


  @Override
  public ArrayList<Book> showAllBooks() {
    return bookDAO.readAllBooks();
  }

  @Override
  public ArrayList<Book> findBooksByTitle(String title) {
    return null;
  }

  @Override
  public ArrayList<Book> findBooksByAuthor(String author) {
    return null;
  }

  @Override
  public ArrayList<Book> findBooksByPublishing(String publishing) {
    return null;
  }

  @Override
  public ArrayList<Book> findBooksByPriceRange(int min, int max) {
    return null;
  }
}
