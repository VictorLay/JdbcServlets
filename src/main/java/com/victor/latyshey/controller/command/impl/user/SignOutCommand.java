package com.victor.latyshey.controller.command.impl.user;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.victor.latyshey.util.ResourceManager;

public class SignOutCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    req.getSession().removeAttribute("user");

    return new CommandResponse(ResourceManager.getProperty("url.home"), true);
  }
}
