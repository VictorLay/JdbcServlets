package com.victor.latyshey.controller.command.impl.user;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ResourceManager;

public class SignOutCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    session.removeAttribute("user");
    session.removeAttribute("login");
    session.removeAttribute("role");

    return new CommandResponse(ResourceManager.getProperty("url.home"), true);
  }
}
