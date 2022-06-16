package com.victor.latyshey.controller.command.impl.action;

import static com.victor.latyshey.controller.command.Param.HOME_PAGE;
import static com.victor.latyshey.controller.command.Param.LANG;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class ChangeLangCommand implements Command {


  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    req.getSession().setAttribute(LANG, req.getParameter(LANG));
    return new CommandResponse(ResourceManager.getProperty(HOME_PAGE), false);
  }
}
