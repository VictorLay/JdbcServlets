package com.victor.latyshey.controller.command.impl.book;

import static com.victor.latyshey.controller.command.Param.COMMON_ERROR_PAGE;
import static com.victor.latyshey.controller.command.Param.ERROR_MESSAGE;
import static com.victor.latyshey.controller.command.Param.HOME_PAGE;
import static com.victor.latyshey.controller.command.Param.SHOW_BOOKS;
import static com.victor.latyshey.util.Validator.checkPermission;

import com.victor.latyshey.bean.user.Role;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.ServiceFactory;
import com.victor.latyshey.util.ObjectExtractor;
import com.victor.latyshey.util.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.victor.latyshey.util.ResourceManager;

public class AddNewBookCommand implements Command {

  private static final Logger logger = LogManager.getLogger(AddNewBookCommand.class);

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {

    try {
      if (checkPermission(Role.EMPLOYEE_USER, req)) {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        bookService.createBook(ObjectExtractor.extractBook(req));
        return new CommandResponse(ResourceManager.getProperty(SHOW_BOOKS), false);
      }
    } catch (ServiceException e) {
      logger.log(Level.ERROR, "The book wasn't been added", e);
      req.setAttribute(ERROR_MESSAGE, "The book wasn't been added");
      return new CommandResponse(ResourceManager.getProperty(COMMON_ERROR_PAGE), false);
    }
    return new CommandResponse(ResourceManager.getProperty(HOME_PAGE), true);
  }
}