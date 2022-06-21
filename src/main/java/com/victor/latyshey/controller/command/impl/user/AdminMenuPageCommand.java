package com.victor.latyshey.controller.command.impl.user;


import static com.victor.latyshey.controller.command.Param.ADMIN_MENU_PAGE;
import static com.victor.latyshey.controller.command.Param.HOME_PAGE;

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
      return new CommandResponse(ResourceManager.getProperty(ADMIN_MENU_PAGE), false);
    }
    return new CommandResponse(ResourceManager.getProperty(HOME_PAGE), false);
  }
}
