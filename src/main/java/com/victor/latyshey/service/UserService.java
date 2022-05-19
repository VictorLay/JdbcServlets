package com.victor.latyshey.service;

import com.victor.latyshey.beans.UserSessionInf;
import com.victor.latyshey.beans.user.User;
import com.victor.latyshey.service.exception.ServiceException;

public interface UserService extends Service {

  User findUser(String login, String password) throws ServiceException;
  boolean isUserExist(UserSessionInf userSessionInf) throws ServiceException;
  void register(User user) throws ServiceException;

}
