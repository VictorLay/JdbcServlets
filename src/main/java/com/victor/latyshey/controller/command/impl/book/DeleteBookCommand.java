package com.victor.latyshey.controller.command.impl.book;

import static com.victor.latyshey.controller.command.Param.BOOK_ID;
import static com.victor.latyshey.controller.command.Param.COMMON_ERROR_PAGE;
import static com.victor.latyshey.controller.command.Param.EMPLOYEE_PERMISSION;
import static com.victor.latyshey.controller.command.Param.ERROR_MESSAGE;
import static com.victor.latyshey.controller.command.Param.HOME_PAGE;
import static com.victor.latyshey.util.Validator.checkPermission;


import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.victor.latyshey.util.ResourceManager;

public class DeleteBookCommand implements Command {

  private static final Logger logger = LogManager.getLogger(DeleteBookCommand.class);

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {

    if (checkPermission(EMPLOYEE_PERMISSION, req)){
      try {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        bookService.deleteBook(Integer.parseInt(req.getParameter(BOOK_ID)));
        return new CommandResponse(ResourceManager.getProperty(HOME_PAGE), false);
      } catch (ServiceException e) {
        logger.log(Level.ERROR, e);
        req.setAttribute(ERROR_MESSAGE, e.toString());
        return new CommandResponse(ResourceManager.getProperty(COMMON_ERROR_PAGE), false);
      }
    }
    logger.log(Level.INFO, "The access was denied.");
    // todo set the error message text
    return new CommandResponse(ResourceManager.getProperty(COMMON_ERROR_PAGE), false);
  }
}
