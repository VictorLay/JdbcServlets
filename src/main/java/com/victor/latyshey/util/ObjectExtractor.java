package com.victor.latyshey.util;

import com.victor.latyshey.bean.book.Author;
import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.bean.book.Genre;
import com.victor.latyshey.bean.book.Publishing;
import com.victor.latyshey.bean.builder.BookBuilder;
import javax.servlet.http.HttpServletRequest;

public class ObjectExtractor {

  /**
   * The method is extracts object Book from servlet request.
   * @param req
   * @return
   */
  public static Book extractBook(HttpServletRequest req) {
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
