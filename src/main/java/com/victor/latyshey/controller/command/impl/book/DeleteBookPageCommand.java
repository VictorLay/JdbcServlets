package com.victor.latyshey.controller.command.impl.book;

import static com.victor.latyshey.controller.command.Param.EMPLOYEE_PERMISSION;

import com.victor.latyshey.bean.user.Role;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.util.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class DeleteBookPageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    if (Validator.checkPermission(EMPLOYEE_PERMISSION, req)) {
      return new CommandResponse(ResourceManager.getProperty("page.delete_book_page"), false);
    }
    return new CommandResponse(ResourceManager.getProperty("page.home"), false);
  }
}
