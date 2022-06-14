package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.Transaction;
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

public class DeleteBookCommand implements Command {

  private static final Logger logger = LogManager.getLogger(DeleteBookCommand.class);

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {

    //todo add role validation
    try {
      BookService bookService = ServiceFactory.getInstance().getBookService();
      bookService.deleteBook(Integer.parseInt(req.getParameter("id")));
      return new CommandResponse(ResourceManager.getProperty("page.home"), false);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      req.setAttribute("exceptionText", e.toString());
      return new CommandResponse(ResourceManager.getProperty("page.common_error"), false);
    }
  }
}
