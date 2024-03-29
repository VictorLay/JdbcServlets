package com.victor.latyshey.service;

import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.service.exception.ServiceException;
import java.util.List;

public interface BookService {

  List<Book> showBooks() throws ServiceException;
  Book showBook(Integer id) throws ServiceException;
  void createBook(Book book) throws ServiceException;
  void updateBook(Book book) throws ServiceException;
  void deleteBook(Integer id) throws ServiceException;
}
