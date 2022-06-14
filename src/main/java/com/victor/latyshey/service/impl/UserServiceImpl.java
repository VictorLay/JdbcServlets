package com.victor.latyshey.service.impl;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.bean.user.User;
import com.victor.latyshey.dao.UserDAO;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.Transaction;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import java.io.IOException;

public class UserServiceImpl implements UserService {


  UserServiceImpl() {
  }

  @Override
  public User findUser(String login, String password) throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction()) {
      UserDAO userDAO = transaction.getUserDao();
      return userDAO.read(login, password);
    } catch (DaoException | IOException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean isUserExist(UserSessionInf userSession) throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction()) {
      User user = transaction.getUserDao().read(userSession.getId());
      if (user == null) {
        return false;
      }
      return userSession.getLogin().equals(user.getLogin()) && userSession.getRole()
          .equals(user.getRole().getRoleName().getValue());
    } catch (DaoException | IOException e) {
      throw new ServiceException();
    }
  }

  @Override
  public void register(User user) throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction()) {
      transaction.getUserDao().create(user);
    } catch (DaoException | IOException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public void changeRole(User user) throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction()) {
      transaction.getUserDao().update(user);
      transaction.commit();
    } catch (DaoException | IOException e) {
      throw new ServiceException(e);
    }

  }
}
