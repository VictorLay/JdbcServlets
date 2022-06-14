package com.victor.latyshey.controller.command.impl.book;

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

  private final Logger logger;

  public ShowBooksPageCommand() {
    logger = LogManager.getLogger();
  }

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    try {
      BookService bookService = ServiceFactory.getInstance().getBookService();

      req.setAttribute("books", bookService.showBooks());

    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
    }

    return new CommandResponse(ResourceManager.getProperty("page.books_showing"), false);
  }
}
