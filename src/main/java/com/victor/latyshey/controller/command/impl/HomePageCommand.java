package com.victor.latyshey.controller.command.impl;

import static com.victor.latyshey.controller.command.Param.HOME_PAGE;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.util.ResourceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    return new CommandResponse(ResourceManager.getProperty(HOME_PAGE), false);
  }
}
