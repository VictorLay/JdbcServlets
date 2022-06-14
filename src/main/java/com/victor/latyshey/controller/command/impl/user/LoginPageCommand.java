package com.victor.latyshey.controller.command.impl.user;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.ServiceFactory;
import com.victor.latyshey.service.impl.UserServiceImpl;
import com.victor.latyshey.util.Validator;
import com.victor.latyshey.validation.SessionInfoValidator;
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
        return new CommandResponse(ResourceManager.getProperty("page.user_home_page"), false);
      }
      return new CommandResponse(ResourceManager.getProperty("page.login"), false);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty("page.enter_error"), false);
    }
  }

}
