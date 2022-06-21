package com.victor.latyshey.controller.command;

import com.victor.latyshey.bean.user.Role;

public class Param {
  private Param() {
  }
  public static final Role EMPLOYEE_PERMISSION = Role.EMPLOYEE_USER;
  public static final Role DEFAULT_ROLE = Role.SIGNED_USER;
  public static final String SESSION_USER_INFO = "user";
  public static final String LOGIN = "login";
  public static final String PASSWORD = "password";
  public static final String ROLE = "role";
  public static final String BOOK = "book";
  public static final String BOOKS = "books";
  public static final String LANG = "language";
  public static final String HOME_PAGE = "page.home";

  public static final String COMMON_ERROR_PAGE = "page.common_error";
  public static final String LOGIN_ERROR_PAGE = "page.enter_error";
  public static final String BOOK_CREATE_PAGE = "page.book_creating";
  public static final String DELETE_BOOK_PAGE = "page.delete_book_page";
  public static final String SHOW_BOOKS = "path.books";
  public static final String SHOW_ALL_BOOKS_PAGE = "page.books_showing";
  public static final String SHOW_ONE_BOOK_PAGE = "page.book_showing";
  public static final String BOOK_ID = "book_id";
  public static final String ERROR_MESSAGE = "exceptionText";
  public static final String UPDATE_BOOK_PAGE = "page.update_book";
  public static final String ADMIN_MENU_PAGE = "page.admin_menu";
  public static final String EMPLOYEE_MENU_PAGE = "page.employee_menu";
  public static final String USER_HOME_PAGE = "page.user_home_page";
  public static final String LOGIN_PAGE = "page.login";
  public static final String REGISTRATION_PAGE = "page.registration";

  public static final String URL_HOME="url.home";





}
