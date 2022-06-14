package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.BookServiceImpl;
import com.victor.latyshey.util.ObjectExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.victor.latyshey.util.ResourceManager;

public class UpdateBookCommand implements Command {

  private static final Logger logger = LogManager.getLogger(UpdateBookCommand.class);

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    try {
      BookService bookService = new BookServiceImpl();
      bookService.updateBook(ObjectExtractor.extractBook(req));
      Book book = bookService.showBook(Integer.parseInt(req.getParameter("isbn")));
      req.getSession().setAttribute("book", book);
      return new CommandResponse(ResourceManager.getProperty("page.book_showing"),false);
    }catch (ServiceException | RuntimeException e){
      logger.log(Level.ERROR, e);
      req.setAttribute("exceptionText", e.toString());
      return new CommandResponse(ResourceManager.getProperty("page.common_error"), false);
    }
  }

}
