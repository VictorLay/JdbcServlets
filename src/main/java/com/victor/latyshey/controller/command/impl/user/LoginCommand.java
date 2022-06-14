package com.victor.latyshey.controller.command.impl.user;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.bean.user.User;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.ServiceFactory;
import com.victor.latyshey.service.impl.UserServiceImpl;
import com.victor.latyshey.validation.SessionInfoValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.victor.latyshey.util.ResourceManager;

public class LoginCommand implements Command {

  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";
  private static final String ROLE = "role";
  private static final String SESSION_USER_INFO = "user";
  private static final Logger logger = LogManager.getLogger();


  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    try {
      UserService userService = ServiceFactory.getInstance().getUserService();

      if (!isAuthorized(req, userService)) {
        setSessionAttributes(req, userService);
      }
      return new CommandResponse(ResourceManager.getProperty("page.user_home_page"), false);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, "The attempt of login has complete incorrect!", e);
      return new CommandResponse(ResourceManager.getProperty("page.enter_error"), false);
    }
  }


  private boolean isAuthorized(HttpServletRequest req, UserService userService)
      throws ServiceException {
    if (req.getSession() == null) {
      return false;
    }

    UserSessionInf user = (UserSessionInf) req.getSession().getAttribute(SESSION_USER_INFO);
    SessionInfoValidator validator = new SessionInfoValidator();
    if (validator.isValid(user) && user.getLogin()
        .equals(req.getSession().getAttribute(LOGIN)) && user.getRole()
        .equals(req.getSession().getAttribute(ROLE))) {
      return userService.isUserExist(user);
    } else {
      logger.log(Level.DEBUG, validator.isValid(user));
      return false;
    }
  }

  private void setSessionAttributes(HttpServletRequest req, UserService userService)
      throws ServiceException {
    User user = userService.findUser(req.getParameter(LOGIN), req.getParameter(PASSWORD));
    UserSessionInf sessionInf = new UserSessionInf(user.getLogin(),
        user.getRole().getRoleName().getValue(), user.getId());

    req.getSession().setAttribute(LOGIN, user.getLogin());
    req.getSession().setAttribute(ROLE, user.getRole().getRoleName().getValue());
    req.getSession().setAttribute(SESSION_USER_INFO, sessionInf);
  }

}
