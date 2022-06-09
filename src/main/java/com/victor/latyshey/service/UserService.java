package com.victor.latyshey.service;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.bean.user.User;
import com.victor.latyshey.service.exception.ServiceException;

public interface UserService extends Service {

  User findUser(String login, String password) throws ServiceException;
  boolean isUserExist(UserSessionInf userSessionInf) throws ServiceException;
  void register(User user) throws ServiceException;

  void changeRole(User user) throws ServiceException;

}
