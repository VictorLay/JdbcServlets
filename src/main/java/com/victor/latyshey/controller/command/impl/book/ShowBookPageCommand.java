package com.victor.latyshey.controller.command.impl.book;

import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.service.impl.BookServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.victor.latyshey.util.ResourceManager;

public class ShowBookPageCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    BookService bookService = null;
    try {
      bookService = new BookServiceImpl(TransactionFactory.getInstance().getTransaction());
      Integer bookId = Integer.parseInt(req.getParameter("book_id"));
      Book book = bookService.showBook(bookId);
      req.setAttribute("book", book);
    } catch (DaoException | ServiceException e) {
      //todo add error log
    } finally {
      if (bookService != null) {
        bookService.releaseTheConnection();
      }
    }

    return new CommandResponse(ResourceManager.getProperty("page.book_showing"), false);
  }
}
