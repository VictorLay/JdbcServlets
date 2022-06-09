package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.bean.book.Author;
import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.bean.book.Genre;
import com.victor.latyshey.bean.book.Publishing;
import com.victor.latyshey.bean.builder.BookBuilder;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.BookServiceImpl;
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
    BookService bookService = null;
    try {
      bookService = new BookServiceImpl(TransactionFactory.getInstance().getTransaction());
      bookService.createBook(createBook(req));

    } catch (ServiceException | DaoException e) {
      logger.log(Level.ERROR, "The book wasn't been added");
    } finally {
      if (bookService != null) {
        bookService.releaseTheConnection();
      }
    }
    return new CommandResponse(ResourceManager.getProperty("path.books"), true);
  }

  private Book createBook(HttpServletRequest req) {
    BookBuilder builder = new BookBuilder();
    builder.setId(Integer.parseInt(req.getParameter("isbn")));
    builder.setTitle(String.valueOf(req.getParameter("title")));
    builder.setGenre(new Genre(Integer.parseInt(req.getParameter("genreName"))));
    builder.setPublishing(new Publishing(Integer.parseInt(req.getParameter("publishingName"))));
    builder.setYear(Integer.parseInt(req.getParameter("year")));
    builder.setPrice(Float.parseFloat(req.getParameter("price")));
    builder.addAuthor(new Author(String.valueOf(req.getParameter("author_name")),
        String.valueOf(req.getParameter("author_country"))));
    Book book = builder.getResult();
    builder.reset();
    return book;
  }
}
