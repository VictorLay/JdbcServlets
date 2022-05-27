package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.beans.user.NameOfRole;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ResourceManager;

public class DeleteBookPageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    String role = (String) req.getSession().getAttribute("role");
    if (NameOfRole.EMPLOYEE_USER.getValue().equals(role)) {
      return new CommandResponse(ResourceManager.getProperty("page.delete_book_page"), false);
    }
    return new CommandResponse(ResourceManager.getProperty("page.home"), false);
  }
}
