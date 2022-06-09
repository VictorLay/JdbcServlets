package com.victor.latyshey.dao;

import com.victor.latyshey.bean.Entity;
import com.victor.latyshey.dao.exception.DaoException;

public interface DAO<Type extends Entity> {

  Integer create(Type entity) throws DaoException;

  Type read(Integer id) throws DaoException;

  void update(Type entity) throws DaoException;

  void delete(Integer id) throws DaoException;

}
