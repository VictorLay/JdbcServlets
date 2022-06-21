package com.victor.latyshey.controller.command.impl.book;

import static com.victor.latyshey.controller.command.Param.BOOK;
import static com.victor.latyshey.controller.command.Param.BOOK_ID;
import static com.victor.latyshey.controller.command.Param.COMMON_ERROR_PAGE;
import static com.victor.latyshey.controller.command.Param.ERROR_MESSAGE;
import static com.victor.latyshey.controller.command.Param.SHOW_ONE_BOOK_PAGE;

import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.ServiceFactory;
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
      BookService bookService = ServiceFactory.getInstance().getBookService();
      bookService.updateBook(ObjectExtractor.extractBook(req));
      Book book = bookService.showBook(Integer.parseInt(req.getParameter(BOOK_ID)));
      req.getSession().setAttribute(BOOK, book);
      return new CommandResponse(ResourceManager.getProperty(SHOW_ONE_BOOK_PAGE),false);
    }catch (ServiceException | RuntimeException e){
      logger.log(Level.ERROR, e);
      req.setAttribute(ERROR_MESSAGE, "Error of update");
      return new CommandResponse(ResourceManager.getProperty(COMMON_ERROR_PAGE), false);
    }
  }

}
