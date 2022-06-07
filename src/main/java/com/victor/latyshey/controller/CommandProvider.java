package com.victor.latyshey.controller;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.impl.book.AddNewBookCommand;
import com.victor.latyshey.controller.command.impl.book.AddNewBookPageCommand;
import com.victor.latyshey.controller.command.impl.book.DeleteBookCommand;
import com.victor.latyshey.controller.command.impl.book.DeleteBookPageCommand;
import com.victor.latyshey.controller.command.impl.book.UpdateBookCommand;
import com.victor.latyshey.controller.command.impl.book.UpdateBookPageCommand;
import com.victor.latyshey.controller.command.impl.user.AdminMenuPageCommand;
import com.victor.latyshey.controller.command.impl.user.EmployeeMenuPageCommand;
import com.victor.latyshey.controller.command.impl.user.LoginCommand;
import com.victor.latyshey.controller.command.impl.user.LoginPageCommand;
import com.victor.latyshey.controller.command.impl.user.RegistrationCommand;
import com.victor.latyshey.controller.command.impl.user.RegistrationPageCommand;
import com.victor.latyshey.controller.command.impl.book.ShowBookPageCommand;
import com.victor.latyshey.controller.command.impl.book.ShowBooksPageCommand;
import com.victor.latyshey.controller.command.impl.user.SignOutCommand;
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
    commandStorage.put("book_showing", new ShowBooksPageCommand());
    commandStorage.put("sign_out", new SignOutCommand());
    commandStorage.put("show_book", new ShowBookPageCommand());
    commandStorage.put("update_book", new UpdateBookCommand());
    commandStorage.put("update_book_page", new UpdateBookPageCommand());
    commandStorage.put("delete_book", new DeleteBookCommand());
    commandStorage.put("delete_book_page", new DeleteBookPageCommand());
    commandStorage.put("employee_menu_page", new EmployeeMenuPageCommand());
    commandStorage.put("admin_menu_page", new AdminMenuPageCommand());
  }

  public static Command getCommand(String commandName) {
    return commandStorage.get(commandName);
  }

}
