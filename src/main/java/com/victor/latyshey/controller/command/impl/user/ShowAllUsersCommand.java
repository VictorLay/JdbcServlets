package com.victor.latyshey.controller.command.impl.user;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllUsersCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    req.getSession().getAttribute("role");
    return null;
  }
}
