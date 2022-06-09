package com.victor.latyshey.controller.command.impl.action;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class ChangeLangCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    req.getSession().setAttribute("language", req.getParameter("language"));
    System.out.println(req.getParameter("language"));
    System.out.println( req.getSession().getAttribute("language"));
    return new CommandResponse(ResourceManager.getProperty("page.home"), false);
  }
}
