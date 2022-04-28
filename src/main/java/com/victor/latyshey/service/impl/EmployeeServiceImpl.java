package com.victor.latyshey.service.impl;

import com.victor.latyshey.beans.Book;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.factory.DaoFactory;
import com.victor.latyshey.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

  private DaoFactory daoFactory = DaoFactory.getInstance();
  private BookDAO bookDAO = daoFactory.getBookDAO();


  @Override
  public void addBookToStorage(Book book) {
    bookDAO.createBook(book);
  }

  @Override
  public void deleteBookFromStorage(int isbn) {
    bookDAO.deleteBookByIsbn(isbn);
  }

  @Override
  public void updateBookInStorage(Book book) {
    bookDAO.updateBook(book);

  }
}
