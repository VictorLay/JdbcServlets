package com.victor.latyshey.controller;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.impl.AddNewBookCommand;
import com.victor.latyshey.controller.command.impl.AddNewBookPageCommand;
import com.victor.latyshey.controller.command.impl.LoginCommand;
import com.victor.latyshey.controller.command.impl.LoginPageCommand;
import com.victor.latyshey.controller.command.impl.RegistrationCommand;
import com.victor.latyshey.controller.command.impl.RegistrationPageCommand;
import com.victor.latyshey.controller.command.impl.ShowBookPageCommand;
import com.victor.latyshey.controller.command.impl.ShowBooksCommand;
import com.victor.latyshey.controller.command.impl.SignOutCommand;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

  private static final Map<String, Command> commandStorage = new HashMap<>();

  static {
    commandStorage.put("registration", new RegistrationCommand());
    commandStorage.put("registration_page", new RegistrationPageCommand());
    commandStorage.put("login_page", new LoginPageCommand());
    commandStorage.put("login", new LoginCommand());
    commandStorage.put("add_new_book_page", new AddNewBookPageCommand());
    commandStorage.put("add_new_book", new AddNewBookCommand());
    commandStorage.put("book_showing", new ShowBooksCommand());
    commandStorage.put("sign_out", new SignOutCommand());
    commandStorage.put("show_book", new ShowBookPageCommand());
  }

  public static Command getCommand(String commandName) {
    return commandStorage.get(commandName);
  }

}
