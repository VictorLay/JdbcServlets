package com.victor.latyshey.controller.command.impl.user;


import com.victor.latyshey.bean.user.Role;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.util.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class AdminMenuPageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    if (Validator.checkPermission(Role.ADMIN_USER, req)){
      return new CommandResponse(ResourceManager.getProperty("page.admin_menu"), false);
    }
    return new CommandResponse(ResourceManager.getProperty("page.home"), false);
  }
}
