package com.victor.latyshey.controller.command.impl;

import com.victor.latyshey.beans.UserSessionInf;
import com.victor.latyshey.beans.user.User;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.UserServiceImpl;
import com.victor.latyshey.validation.SessionInfoValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ResourceManager;

public class LoginCommand implements Command {
  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";
  private static final String ROLE = "role";
  private static final String SESSION_USER_INFO = "user";
  private final UserService userService;
  private final Logger logger;

  public LoginCommand() {
    userService = new UserServiceImpl();
    logger = LogManager.getLogger();
  }

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    if (session != null && isUserHasAuthorized(session)) {
      return new CommandResponse(ResourceManager.getProperty("page.user_home_page"), false);
    }
    try {
      setSessionAttributes(req);
      return new CommandResponse(ResourceManager.getProperty("page.user_home_page"), false);

    } catch (ServiceException e) {

      logger.log(Level.ERROR, "The attempt of login has complete incorrect!");
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty("page.enter_error"), false);
    }
  }

  private boolean isUserHasAuthorized(HttpSession session) {

    String login = (String) session.getAttribute(LOGIN);
    String role = (String) session.getAttribute(ROLE);
    UserSessionInf user = (UserSessionInf) session.getAttribute(SESSION_USER_INFO);
    SessionInfoValidator validator = new SessionInfoValidator();

    if (validator.isValid(user).isEmpty() && user.getLogin().equals(login) && user.getRole().equals(role)) {
      try {
        return userService.isUserExist(user);
      } catch (ServiceException e) {
        logger.log(Level.ERROR, e);
        return false;
      }
    }else {
      logger.log(Level.DEBUG, validator.isValid(user));
      return false;
    }
  }

  private void setSessionAttributes(HttpServletRequest req) throws ServiceException {
    User user = userService.findUser(req.getParameter(LOGIN), req.getParameter(PASSWORD));

    UserSessionInf sessionInf = new UserSessionInf(user.getLogin(),
        user.getRole().getRoleName().getValue(), user.getId());

    req.getSession().setAttribute(LOGIN, user.getLogin());
    req.getSession().setAttribute(ROLE, user.getRole().getRoleName().getValue());
    req.getSession().setAttribute(SESSION_USER_INFO, sessionInf);
  }

}
