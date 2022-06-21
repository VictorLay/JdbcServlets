package com.victor.latyshey.controller.command.impl.book;

import static com.victor.latyshey.controller.command.Param.BOOK;
import static com.victor.latyshey.controller.command.Param.BOOK_ID;
import static com.victor.latyshey.controller.command.Param.COMMON_ERROR_PAGE;
import static com.victor.latyshey.controller.command.Param.SHOW_ONE_BOOK_PAGE;

import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowBookPageCommand implements Command {
  private static final Logger logger = LogManager.getLogger(ShowBookPageCommand.class);

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    try {
      BookService bookService = ServiceFactory.getInstance().getBookService();
      Integer bookId = Integer.parseInt(req.getParameter(BOOK_ID));
      Book book = bookService.showBook(bookId);
      req.setAttribute(BOOK, book);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty(COMMON_ERROR_PAGE), false);
    }

    return new CommandResponse(ResourceManager.getProperty(SHOW_ONE_BOOK_PAGE), false);
  }
}
