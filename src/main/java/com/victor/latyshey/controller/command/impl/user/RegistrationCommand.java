package com.victor.latyshey.controller.command.impl.user;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.bean.user.NameOfRole;
import com.victor.latyshey.bean.user.Role;
import com.victor.latyshey.bean.user.User;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.victor.latyshey.util.ResourceManager;

public class RegistrationCommand implements Command {

  private static final String PASSWORD= "password";
  private static final String LOGIN= "login";
  private static final String ROLE= "role";
  private static final NameOfRole DEFAULT_ROLE= NameOfRole.SIGNED_USER;
  private static final String SESSION_USER_INFO= "user";
  private final Logger logger;

  public RegistrationCommand() {
    logger = LogManager.getLogger();
  }

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    UserService userService = null;
    try {
      userService = new UserServiceImpl(TransactionFactory.getInstance().getTransaction());
      userService.register(new User(req.getParameter(LOGIN), req.getParameter(PASSWORD), new Role(DEFAULT_ROLE)));

      User user = userService.findUser(req.getParameter(LOGIN), req.getParameter(PASSWORD));
      initUserSession(req, user);

      return new CommandResponse(ResourceManager.getProperty("page.user_home_page"), false);
    } catch (ServiceException | DaoException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty("page.enter_error"),false);
    }finally {
      if (userService != null) {
        userService.releaseTheConnection();
      }
    }
  }

  private void initUserSession(HttpServletRequest req, User user) {
    UserSessionInf sessionInf = new UserSessionInf(user.getLogin(),
        user.getRole().getRoleName().getValue(), user.getId());

    req.getSession().setAttribute(LOGIN, user.getLogin());
    req.getSession().setAttribute(ROLE, user.getRole().getRoleName().getValue());
    req.getSession().setAttribute(SESSION_USER_INFO, sessionInf);
  }
}
