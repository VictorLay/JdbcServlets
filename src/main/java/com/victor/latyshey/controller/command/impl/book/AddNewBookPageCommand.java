package com.victor.latyshey.controller.command.impl.book;

import static com.victor.latyshey.controller.command.Param.BOOK_CREATE_PAGE;
import static com.victor.latyshey.controller.command.Param.EMPLOYEE_PERMISSION;
import static com.victor.latyshey.controller.command.Param.LOGIN_ERROR_PAGE;
import static com.victor.latyshey.util.Validator.checkPermission;


import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class AddNewBookPageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {

    if (checkPermission(EMPLOYEE_PERMISSION, req)) {
      return new CommandResponse(ResourceManager.getProperty(BOOK_CREATE_PAGE), false);
    }
    return new CommandResponse(ResourceManager.getProperty(LOGIN_ERROR_PAGE), false);
  }

}
