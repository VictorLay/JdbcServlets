package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.BookServiceImpl;
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
      Integer bookId = Integer.parseInt(req.getParameter("book_id"));
      Book book = bookService.showBook(bookId);
      req.setAttribute("book", book);
    } catch (ServiceException e) {
      logger.log(Level.ERROR, e);
      return new CommandResponse(ResourceManager.getProperty("page.common_error"), false);
    }

    return new CommandResponse(ResourceManager.getProperty("page.book_showing"), false);
  }
}
