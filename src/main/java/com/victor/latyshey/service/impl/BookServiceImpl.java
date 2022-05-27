package com.victor.latyshey.service.impl;

import com.victor.latyshey.beans.book.Author;
import com.victor.latyshey.beans.book.Book;
import com.victor.latyshey.controller.command.impl.book.UpdateBookCommand;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.Transaction;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookServiceImpl extends ServiceImpl implements BookService {
  private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);


  public BookServiceImpl(Transaction transaction) {
    super(transaction);
  }



  @Override
  public List<Book> showBooks() throws ServiceException {
    try {
      return transaction.getBookDao().readAllBooks();
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public Book showBook(Integer id) throws ServiceException {
    BookDAO bookDao = transaction.getBookDao();
    Book book;
    try {
      book = bookDao.read(id);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return book;
  }

  @Override
  public void createBook(Book book) throws ServiceException {
    try {
      setAuthorsAccordingToDataBase(book);
      transaction.getBookDao().create(book);
      transaction.commit();
    }catch (DaoException e){
      try {
        transaction.rollback();
      } catch (DaoException ex) {
        throw new ServiceException(ex);
      }
      throw new ServiceException(e);
    }
  }

  @Override
  public void updateBook(Book book) throws ServiceException {
    try {
      setAuthorsAccordingToDataBase(book);
      BookDAO bookDao = transaction.getBookDao();
      bookDao.update(book);
      transaction.commit();
    }catch (DaoException e){
      logger.log(Level.ERROR, e);
      try {
        transaction.rollback();
      } catch (DaoException ex) {
        throw new ServiceException(ex);
      }
      throw new ServiceException(e);
    }
  }

  private void setAuthorsAccordingToDataBase(Book book) throws DaoException {
    for (Author author: book.getAuthors()) {
      Author authorFromDB = transaction.getAuthorDao().readByName(author.getName());
      if (authorFromDB == null) {
        author.setId(transaction.getAuthorDao().create(new Author(author.getName(), author.getCountry())));
        continue;
      }
      author.setId(authorFromDB.getId());
      author.setCountry(authorFromDB.getCountry());
    }
  }

//  private void updateAuthorsInDateBase(Book book) throws DaoException{
//    for (Author author: book.getAuthors()) {
//      Author authorFromDB = transaction.getAuthorDao().readByName(author.getName());
//      if (authorFromDB == null) {
//        author.setId(transaction.getAuthorDao().create(new Author(author.getName(), author.getCountry())));
//        continue;
//      }
//      author.setId(authorFromDB.getId());
//      author.setCountry(authorFromDB.getCountry());
//    }
//  }

}
