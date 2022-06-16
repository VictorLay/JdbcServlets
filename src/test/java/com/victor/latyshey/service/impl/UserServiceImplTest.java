package com.victor.latyshey.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.bean.user.Role;
import com.victor.latyshey.bean.user.User;
import com.victor.latyshey.dao.AuthorDao;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.UserDAO;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.factory.FactoryDAO;
import com.victor.latyshey.dao.impl.AuthorDaoImpl;
import com.victor.latyshey.dao.impl.BookDAOImpl;
import com.victor.latyshey.dao.impl.UserDaoImpl;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserServiceImplTest {

  UserDAO userDAO = Mockito.mock(UserDaoImpl.class);
  AuthorDao authorDao = Mockito.mock(AuthorDaoImpl.class);
  BookDAO bookDAO = Mockito.mock(BookDAOImpl.class);
  String testLogin = "login";
  String testPassword = "password";
  String testRole = "admin_user";
  UserService userService;
  User expectedUser = new User(testLogin, testPassword, Role.ADMIN_USER);

  /*
    Initialization code block.
    In this block with helping of reflection is Mock private fields of FactoryDAO.
   */
  {
    try {
      FactoryDAO.getInstance();
      Field userDAaoField = FactoryDAO.class.getDeclaredField("userDAO");
      Field authorDAaoField = FactoryDAO.class.getDeclaredField("authorDao");
      Field bookDAaoField = FactoryDAO.class.getDeclaredField("bookDAO");
      Field factoryInstanceStaticField = FactoryDAO.class.getDeclaredField("instance");
      userDAaoField.setAccessible(true);
      authorDAaoField.setAccessible(true);
      bookDAaoField.setAccessible(true);
      factoryInstanceStaticField.setAccessible(true);

      FactoryDAO factoryDAO = (FactoryDAO) factoryInstanceStaticField.get(null);

      userDAaoField.set(factoryDAO, userDAO);
      authorDAaoField.set(factoryDAO, authorDao);
      bookDAaoField.set(factoryDAO, bookDAO);


    } catch (NoSuchFieldException | IllegalAccessException e) {
//       replace with log
//      System.out.println("Шеф, всё пропало!");
//      logger.log(lvl.error, "You have some problem with reflection initialization");
      throw new RuntimeException(e);
    }

    userService = new UserServiceImpl();
  }


  @Test
  void findUserTest() throws DaoException, ServiceException {

    when(userDAO.read(testLogin, testPassword)).thenReturn(expectedUser);

    User actualUser = userService.findUser(testLogin, testPassword);

    assertEquals(expectedUser, actualUser);
  }

  @Test
  void isUserExistTest() throws ServiceException, DaoException {
    User user = new User(testLogin, Role.ADMIN_USER, 1);
    when(userDAO.read(1)).thenReturn(user);

    assertEquals(true, userService.isUserExist(new UserSessionInf(testLogin, testRole, 1)));
  }

  @Test
  void register() {
  }

  @Test
  void changeRole() {
  }
}