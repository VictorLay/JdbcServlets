package com.victor.latyshey.controller.command.impl.user;

import static com.victor.latyshey.controller.command.Param.REGISTRATION_PAGE;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class RegistrationPageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    return new CommandResponse(ResourceManager.getProperty(REGISTRATION_PAGE),false);
  }
}
