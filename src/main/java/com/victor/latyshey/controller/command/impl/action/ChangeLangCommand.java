package com.victor.latyshey.controller.command.impl.action;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class ChangeLangCommand implements Command {

  private static final String LANG = "language";

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    req.getSession().setAttribute(LANG, req.getParameter(LANG));
    return new CommandResponse(ResourceManager.getProperty("page.home"), false);
  }
}
