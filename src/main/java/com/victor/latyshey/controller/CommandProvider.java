package com.victor.latyshey.controller;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.impl.LoginCommand;
import com.victor.latyshey.controller.command.impl.LoginPageCommand;
import com.victor.latyshey.controller.command.impl.Registration;
import com.victor.latyshey.controller.command.impl.RegistrationPageCommand;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

  private static final Map<String, Command> commandStorage = new HashMap<>();

  static {
    commandStorage.put("registration", new Registration());
    commandStorage.put("registration_page", new RegistrationPageCommand());
    commandStorage.put("login_page", new LoginPageCommand());
    commandStorage.put("login", new LoginCommand());
  }

  public static Command getCommand(String commandName) {
    return commandStorage.get(commandName);
  }

}
