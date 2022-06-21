package com.victor.latyshey.controller.command.impl.user;

import static com.victor.latyshey.controller.command.Param.EMPLOYEE_MENU_PAGE;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;
import org.apache.logging.log4j.Level;

public class EmployeeMenuPageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    logger.log(Level.INFO, "You in the EmployeeMenuPageCommand.java");
    return new CommandResponse(ResourceManager.getProperty(EMPLOYEE_MENU_PAGE),false);
  }
}
