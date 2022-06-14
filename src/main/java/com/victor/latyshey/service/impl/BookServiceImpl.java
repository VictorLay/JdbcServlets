package com.victor.latyshey.service.impl;

import com.victor.latyshey.bean.book.Author;
import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.exception.DaoException;
import com.victor.latyshey.dao.transaction.Transaction;
import com.victor.latyshey.dao.transaction.TransactionFactory;
import com.victor.latyshey.service.BookService;
import com.victor.latyshey.service.exception.ServiceException;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookServiceImpl implements BookService {
  private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

  BookServiceImpl() {
  }

  @Override
  public List<Book> showBooks() throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction())  {
      return transaction.getBookDao().readAllBooks();
    } catch (DaoException | IOException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public Book showBook(Integer id) throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction()) {
      return transaction.getBookDao().read(id);
    } catch (DaoException | IOException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public void createBook(Book book) throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction())  {
      setAuthorsAccordingToDataBase(book, transaction);
      transaction.getBookDao().create(book);
      transaction.commit();
    }catch (DaoException | IOException e){
      throw new ServiceException(e);
    }
  }

  @Override
  public void updateBook(Book book) throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction())  {
      setAuthorsAccordingToDataBase(book, transaction);
      BookDAO bookDao = transaction.getBookDao();
      bookDao.update(book);
      transaction.commit();
    }catch (DaoException | IOException e){
      logger.log(Level.ERROR, e);
      throw new ServiceException(e);
    }
  }

  @Override
  public void deleteBook(Integer id) throws ServiceException {
    try(Transaction transaction = TransactionFactory.getInstance().getTransaction())  {
      transaction.getBookDao().delete(id);
      transaction.commit();
    } catch (DaoException | IOException e) {
      throw new ServiceException(e);
    }
  }

  private void setAuthorsAccordingToDataBase(Book book, Transaction transaction) throws DaoException {
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
