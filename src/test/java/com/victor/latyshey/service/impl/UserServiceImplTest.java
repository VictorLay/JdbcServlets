package com.victor.latyshey.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.victor.latyshey.bean.user.Role;
import com.victor.latyshey.bean.user.User;
import com.victor.latyshey.dao.AuthorDao;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.UserDAO;
import com.victor.latyshey.dao.connection.ConnectionPool;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.factory.DaoFactoryTestsInitializer;
import com.victor.latyshey.dao.factory.FactoryDAO;
import com.victor.latyshey.dao.impl.AuthorDaoImpl;
import com.victor.latyshey.dao.impl.BookDAOImpl;
import com.victor.latyshey.dao.impl.UserDaoImpl;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class UserServiceImplTest {

  UserDAO userDAO = Mockito.mock(UserDaoImpl.class);
  AuthorDao authorDao = Mockito.mock(AuthorDaoImpl.class);
  BookDAO bookDAO = Mockito.mock(BookDAOImpl.class);
  String testLogin = "login";
  String testPassword = "password";
  UserService userService;

  @BeforeEach
  void setUp() {
    DaoFactoryTestsInitializer.initialize(userDAO, authorDao, bookDAO);
    userService = new UserServiceImpl();
  }

  @AfterEach
  void setOut() {
    ConnectionPool.getInstance().destroy();
  }

  @Test
  void findUserTest() throws DaoException, ServiceException {
    User expectedUser = new User(testLogin, testPassword, new Role());
    when(userDAO.read(testLogin, testPassword)).thenReturn(expectedUser);

    User actualUser = userService.findUser(testLogin, testPassword);

    assertEquals(expectedUser, actualUser);
  }

}