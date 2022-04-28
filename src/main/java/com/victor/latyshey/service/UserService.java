package com.victor.latyshey.service;

import com.victor.latyshey.beans.Book;
import java.util.ArrayList;

public interface UserService {

  ArrayList<Book> showAllBooks();
  ArrayList<Book> findBooksByTitle(String title);
  ArrayList<Book> findBooksByAuthor(String author);
  ArrayList<Book> findBooksByPublishing(String publishing);
  ArrayList<Book> findBooksByPriceRange(int min, int max);

}
