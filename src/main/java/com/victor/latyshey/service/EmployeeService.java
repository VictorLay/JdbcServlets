package com.victor.latyshey.service;

import com.victor.latyshey.beans.Book;

public interface EmployeeService {

  void addBookToStorage(Book book);
  void deleteBookFromStorage(int isbn);
  void updateBookInStorage(Book book);

}
