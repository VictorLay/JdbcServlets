package com.victor.latyshey.controller;

import java.util.Optional;
import java.util.stream.Stream;

public enum CommandName {
  REGISTRATION_COMMAND("registration"),
  REGISTRATION_PAGE("registration_page"),
  LOGIN_COMMAND("login"),
  LOGIN_PAGE("login_page"),
  ADD_NEW_BOOK_PAGE("add_new_book_page"),
  ADD_NEW_BOOK_COMMAND("add_new_book"),
  SHOW_ALL_BOOKS_PAGE("book_showing"),
  SIGN_OUT_COMMAND("sign_out"),
  SHOW_ONE_BOOK("show_book"),
  UPDATE_BOOK_COMMAND("update_book"),
  UPDATE_BOOK_PAGE("update_book_page"),
  DELETE_BOOK_COMMAND("delete_book"),
  DELETE_BOOK_PAGE("delete_book_page"),
  EMPLOYEE_MENU_PAGE("employee_menu_page"),
  ADMIN_MENU_PAGE("admin_menu_page"),
  CHANGE_LANGUAGE_COMMAND("change_lang_command"),
  DEFAULT("home_page");

  String value;

  CommandName(String value) {
    this.value = value;
  }
  public static CommandName of(String commandName){
    Optional<CommandName> response = Stream.of(CommandName.values())
        .filter(c -> c.value.equalsIgnoreCase(commandName)).findFirst();
    return response.isPresent()? response.get(): CommandName.DEFAULT;
  }
}


