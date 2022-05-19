package com.victor.latyshey.service.impl;

import com.victor.latyshey.beans.UserSessionInf;
import com.victor.latyshey.beans.user.User;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.Transaction;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;

public class UserServiceImpl extends ServiceImpl implements UserService {

  public UserServiceImpl(Transaction transaction) {
    super(transaction);
  }

  @Override
  public User findUser(String login, String password) throws ServiceException {
    try {
      return transaction.getUserDao().read(login, password);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean isUserExist(UserSessionInf userSession) throws ServiceException {
    try {
      User user = transaction.getUserDao().read(userSession.getId());
      if (user == null) {
        return false;
      }
      return userSession.getLogin().equals(user.getLogin()) && userSession.getRole()
          .equals(user.getRole().getRoleName().getValue());
    } catch (DaoException e) {
      throw new ServiceException();
    }
  }

  @Override
  public void register(User user) throws ServiceException {
    try {
      transaction.getUserDao().create(user);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }

  }
}
