package com.victor.latyshey.dao;

import com.victor.latyshey.beans.book.Author;
import com.victor.latyshey.dao.exception.DaoException;
import java.util.List;

public interface AuthorDao extends DAO<Author> {
//todo rename методы в read и использовать магические константы
  Author readByName(String authorName) throws DaoException;

  List<Author> readByCountry(String country) throws DaoException;

}
