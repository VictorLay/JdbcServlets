package com.victor.latyshey.controller;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.impl.HomePageCommand;
import com.victor.latyshey.controller.command.impl.action.ChangeLangCommand;
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
import java.util.EnumMap;

public class CommandProvider {

  private CommandProvider() {
  }

  private static final EnumMap<CommandName, Command> commands= new EnumMap<>(CommandName.class);

  static {
    commands.put(CommandName.REGISTRATION_COMMAND, new RegistrationCommand());
    commands.put(CommandName.REGISTRATION_PAGE, new RegistrationPageCommand());
    commands.put(CommandName.LOGIN_COMMAND, new LoginCommand());
    commands.put(CommandName.LOGIN_PAGE, new LoginPageCommand());
    commands.put(CommandName.ADD_NEW_BOOK_PAGE, new AddNewBookPageCommand());
    commands.put(CommandName.ADD_NEW_BOOK_COMMAND, new AddNewBookCommand());
    commands.put(CommandName.SHOW_ALL_BOOKS_PAGE, new ShowBooksPageCommand());
    commands.put(CommandName.SIGN_OUT_COMMAND, new SignOutCommand());
    commands.put(CommandName.SHOW_ONE_BOOK, new ShowBookPageCommand());
    commands.put(CommandName.UPDATE_BOOK_COMMAND, new UpdateBookCommand());
    commands.put(CommandName.UPDATE_BOOK_PAGE, new UpdateBookPageCommand());
    commands.put(CommandName.DELETE_BOOK_COMMAND, new DeleteBookCommand());
    commands.put(CommandName.DELETE_BOOK_PAGE, new DeleteBookPageCommand());
    commands.put(CommandName.EMPLOYEE_MENU_PAGE, new EmployeeMenuPageCommand());
    commands.put(CommandName.ADMIN_MENU_PAGE, new AdminMenuPageCommand());
    commands.put(CommandName.CHANGE_LANGUAGE_COMMAND, new ChangeLangCommand());
    commands.put(CommandName.DEFAULT, new HomePageCommand());
  }

  public static Command getCommand(String commandName) {
    return commands.get(CommandName.of(commandName));
  }

}
