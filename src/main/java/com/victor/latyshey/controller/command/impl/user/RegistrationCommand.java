package com.victor.latyshey.controller.command.impl.user;

import static com.victor.latyshey.controller.command.Param.DEFAULT_ROLE;
import static com.victor.latyshey.controller.command.Param.LOGIN;
import static com.victor.latyshey.controller.command.Param.LOGIN_ERROR_PAGE;
import static com.victor.latyshey.controller.command.Param.PASSWORD;
import static com.victor.latyshey.controller.command.Param.SESSION_USER_INFO;
import static com.victor.latyshey.controller.command.Param.USER_HOME_PAGE;

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
  private final Logger logger = LogManager.getLogger();
  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    try {
      UserService userService = ServiceFactory.getInstance().getUserService();
      userService.register(
          new User(req.getParameter(LOGIN), req.getParameter(PASSWORD), DEFAULT_ROLE) );

      User user = userService.findUser(req.getParameter(LOGIN), req.getParameter(PASSWORD));
      initUserSession(req, user);

      return new CommandResponse(ResourceManager.getProperty(USER_HOME_PAGE), false);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty(LOGIN_ERROR_PAGE),false);
    }
  }

  private void initUserSession(HttpServletRequest req, User user) {
    UserSessionInf sessionInf = new UserSessionInf(user.getLogin(),
        user.getRole().toString().toLowerCase(Locale.ROOT), user.getId());

    req.getSession().setAttribute(SESSION_USER_INFO, sessionInf);
  }
}
