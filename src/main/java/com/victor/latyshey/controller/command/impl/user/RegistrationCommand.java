package com.victor.latyshey.controller.command.impl.user;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.bean.user.Role;
import com.victor.latyshey.bean.user.User;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.ServiceFactory;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.victor.latyshey.util.ResourceManager;

public class RegistrationCommand implements Command {

  private static final String PASSWORD= "password";
  private static final String LOGIN= "login";
  private static final Role DEFAULT_ROLE = Role.SIGNED_USER;
  private static final String SESSION_USER_INFO= "user";
  private final Logger logger;

  public RegistrationCommand() {
    logger = LogManager.getLogger();
  }

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    try {
      UserService userService = ServiceFactory.getInstance().getUserService();
      userService.register(
          new User(req.getParameter(LOGIN), req.getParameter(PASSWORD), Role.SIGNED_USER) );

      User user = userService.findUser(req.getParameter(LOGIN), req.getParameter(PASSWORD));
      initUserSession(req, user);

      return new CommandResponse(ResourceManager.getProperty("page.user_home_page"), false);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty("page.enter_error"),false);
    }
  }

  private void initUserSession(HttpServletRequest req, User user) {
    UserSessionInf sessionInf = new UserSessionInf(user.getLogin(),
        user.getRole().toString().toLowerCase(Locale.ROOT), user.getId());

    req.getSession().setAttribute(SESSION_USER_INFO, sessionInf);
  }
}
