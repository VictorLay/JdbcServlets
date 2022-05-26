package com.victor.latyshey.controller.command.impl;

import com.victor.latyshey.beans.UserSessionInf;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.UserServiceImpl;
import com.victor.latyshey.validation.SessionInfoValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ResourceManager;

public class LoginPageCommand implements Command {
  private static final String LOGIN = "login";
  private static final String ROLE = "role";
  private static final String SESSION_USER_INFO = "user";
  private static final Logger logger= LogManager.getLogger();
  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    UserService userService = null;
    try {
      userService = new UserServiceImpl(TransactionFactory.getInstance().getTransaction());
      if (isAuthorized(req, userService)) {
        return new CommandResponse(ResourceManager.getProperty("page.user_home_page"), false);
      }
      return new CommandResponse(ResourceManager.getProperty("page.login"), false);
    } catch (DaoException | ServiceException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty("page.enter_error"), false);
    } finally {
      if (userService != null) {
        userService.releaseTheConnection();
      }
    }
  }

  private boolean isAuthorized(HttpServletRequest req, UserService userService)
      throws ServiceException {
    if (req.getSession() == null) {
      return false;
    }

    UserSessionInf user = (UserSessionInf) req.getSession().getAttribute(SESSION_USER_INFO);
    SessionInfoValidator validator = new SessionInfoValidator();
    if (validator.isValid(user).isEmpty() &&
        user.getLogin().equals(req.getSession().getAttribute(LOGIN)) &&
        user.getRole().equals(req.getSession().getAttribute(ROLE))) {
      return userService.isUserExist(user);
    } else {
      logger.log(Level.DEBUG, validator.isValid(user));
      return false;
    }
  }

}
