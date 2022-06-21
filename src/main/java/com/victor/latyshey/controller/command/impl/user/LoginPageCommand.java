package com.victor.latyshey.controller.command.impl.user;

import static com.victor.latyshey.controller.command.Param.LOGIN_ERROR_PAGE;
import static com.victor.latyshey.controller.command.Param.LOGIN_PAGE;
import static com.victor.latyshey.controller.command.Param.USER_HOME_PAGE;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.ServiceFactory;
import com.victor.latyshey.util.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.victor.latyshey.util.ResourceManager;

public class LoginPageCommand implements Command {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    try {
      UserService userService = ServiceFactory.getInstance().getUserService();
      if (Validator.isAuthorized(req, userService)) {
        return new CommandResponse(ResourceManager.getProperty(USER_HOME_PAGE), false);
      }
      return new CommandResponse(ResourceManager.getProperty(LOGIN_PAGE), false);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty(LOGIN_ERROR_PAGE), false);
    }
  }

}
