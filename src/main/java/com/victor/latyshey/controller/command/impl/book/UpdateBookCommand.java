package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.beans.book.Author;
import com.victor.latyshey.beans.book.Book;
import com.victor.latyshey.beans.book.Genre;
import com.victor.latyshey.beans.book.Publishing;
import com.victor.latyshey.beans.builder.BookBuilder;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.Transaction;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.BookServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ResourceManager;

public class UpdateBookCommand implements Command {

  private static final Logger logger = LogManager.getLogger(UpdateBookCommand.class);

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    Transaction transaction;
    BookService bookService = null;
    try {
      transaction = TransactionFactory.getInstance().getTransaction();
    } catch (DaoException e) {
      logger.log(Level.ERROR, "Get connection error.");
      return new CommandResponse(ResourceManager.getProperty("page.error"), false);
    }

    try {
      bookService = new BookServiceImpl(transaction);
      bookService.updateBook(createBook(req));
      Book book = bookService.showBook(Integer.parseInt(req.getParameter("isbn")));
      req.getSession().setAttribute("book", book);
      return new CommandResponse(ResourceManager.getProperty("page.book_showing"),false);
    }catch (ServiceException | RuntimeException e){
      logger.log(Level.ERROR, e);
      req.setAttribute("exceptionText", e.toString());
      return new CommandResponse(ResourceManager.getProperty("page.common_error"), false);

    }finally {
      if (bookService != null) {
        bookService.releaseTheConnection();
      }
    }

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
