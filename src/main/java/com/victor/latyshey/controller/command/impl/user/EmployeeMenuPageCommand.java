package com.victor.latyshey.controller.command.impl.user;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ResourceManager;

public class EmployeeMenuPageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    return new CommandResponse(ResourceManager.getProperty("page.employee_menu"),false);
  }
}
