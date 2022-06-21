package com.victor.latyshey.controller.command.impl.book;

import static com.victor.latyshey.controller.command.Param.BOOKS;
import static com.victor.latyshey.controller.command.Param.SHOW_ALL_BOOKS_PAGE;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.BookServiceImpl;
import com.victor.latyshey.service.impl.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.victor.latyshey.util.ResourceManager;

public class ShowBooksPageCommand implements Command {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    try {
      BookService bookService = ServiceFactory.getInstance().getBookService();

      req.setAttribute(BOOKS, bookService.showBooks());

    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      //todo add Error Response
      //todo remove all exception Texts from response
    }

    return new CommandResponse(ResourceManager.getProperty(SHOW_ALL_BOOKS_PAGE), false);
  }
}
