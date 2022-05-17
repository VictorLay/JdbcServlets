package com.victor.latyshey.controller.command.impl;

import com.victor.latyshey.beans.UserSessionInf;
import com.victor.latyshey.beans.user.User;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ResourceManager;

public class RegistrationCommand implements Command {

  private static final String PASSWORD= "password";
  private static final String LOGIN= "login";
  private static final String ROLE= "role";
  private static final String SESSION_USER_INFO= "user";
  private final Logger logger;

  public RegistrationCommand() {
    logger = LogManager.getLogger();
  }

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    UserService userService = new UserServiceImpl();

    try {
      userService.register(new User(req.getParameter(LOGIN), req.getParameter(PASSWORD)));
      User user = userService.findUser(req.getParameter(LOGIN), req.getParameter(PASSWORD));
      UserSessionInf sessionInf = new UserSessionInf(user.getLogin(),
          user.getRole().getRoleName().getValue(), user.getId());

      req.getSession().setAttribute(LOGIN, user.getLogin());
      req.getSession().setAttribute(ROLE, user.getRole().getRoleName().getValue());
      req.getSession().setAttribute(SESSION_USER_INFO, sessionInf);

      return new CommandResponse(ResourceManager.getProperty("page.user_home_page"), false);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty("page.enter_error"),false);
    }
  }
}
