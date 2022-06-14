package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.bean.user.NameOfRole;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.validation.SessionInfoValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class AddNewBookPageCommand implements Command {

  private static final String SESSION_USER_INFO = "user";
  private static final NameOfRole PERMISSION = NameOfRole.EMPLOYEE_USER;

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    SessionInfoValidator validator = new SessionInfoValidator();
    UserSessionInf userInfo = (UserSessionInf) req.getSession().getAttribute(SESSION_USER_INFO);
    //todo заменить валидацию для роли
    if ( validator.isValid(userInfo) && PERMISSION.getValue().equals(userInfo.getRole())) {
      return new CommandResponse(ResourceManager.getProperty("page.book_creating"), false);
    }
    return new CommandResponse(ResourceManager.getProperty("page.enter_error"), false);
  }

}
