package com.victor.latyshey.dao;

import com.victor.latyshey.beans.Book;
import java.util.ArrayList;

public interface BookDAO {

  boolean createBook(Book book);
  Book readBookByIsbn(int isbn);
  ArrayList<Book> readAllBooks() ;


  ArrayList<Book> readBooksByTitle(String title);
  ArrayList<Book> readBooksByPrice(int min, int max);
  void updateBook(Book book);
  boolean deleteBookByIsbn(int isbn);
}
