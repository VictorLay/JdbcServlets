package com.victor.latyshey.dao;

import com.victor.latyshey.bean.book.Author;
import com.victor.latyshey.dao.exception.DaoException;
import java.util.List;

public interface AuthorDao extends DAO<Author> {
  Author readByName(String authorName) throws DaoException;

  List<Author> readByCountry(String country) throws DaoException;

}
