package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.BookServiceImpl;
import com.victor.latyshey.service.impl.ServiceFactory;
import com.victor.latyshey.util.ObjectExtractor;
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
      BookService bookService = ServiceFactory.getInstance().getBookService();
      bookService.createBook(ObjectExtractor.extractBook(req));

    } catch (ServiceException e) {
      logger.log(Level.ERROR, "The book wasn't been added");
    }
    return new CommandResponse(ResourceManager.getProperty("path.books"), true);
  }
}
