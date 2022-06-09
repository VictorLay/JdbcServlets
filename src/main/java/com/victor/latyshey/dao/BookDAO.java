package com.victor.latyshey.dao;

import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.dao.exception.DaoException;
import java.util.List;

public interface BookDAO extends DAO<Book> {

  List<Book> readAllBooks() throws DaoException;


//  ArrayList<Book> readBooksByTitle(String title) throws DaoException;
//
//  ArrayList<Book> readBooksByPrice(int min, int max) throws DaoException;
}
