package com.victor.latyshey.dao;

import com.victor.latyshey.beans.User;
import com.victor.latyshey.dao.exception.DaoException;

public interface UserDAO extends DAO<User>{
  User read(String login, String password) throws DaoException;

}
