package com.victor.latyshey.service.impl;

import com.victor.latyshey.beans.book.Author;
import com.victor.latyshey.beans.book.Book;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.Transaction;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import java.util.List;

public class BookServiceImpl extends ServiceImpl implements BookService {


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
}
