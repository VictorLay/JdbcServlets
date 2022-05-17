package com.victor.latyshey.dao.impl;

import com.victor.latyshey.beans.book.Book;
import com.victor.latyshey.beans.book.Genre;
import com.victor.latyshey.beans.book.Publishing;
import com.victor.latyshey.beans.builder.BookBuilder;
import com.victor.latyshey.connection.ConnectionPool;
import com.victor.latyshey.connection.PoolException;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.exception.DaoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
  public static final String ID = "id";
  public static final String TITLE = "title";
  public static final String GENRE_ID = "genre_id";
  public static final String GENRE = "genre";
  public static final String PUBLISHING_ID = "publishing_id";
  public static final String PUBLISHING = "publishing";
  public static final String YEAR = "year";
  public static final String PRICE = "price";
  private static final String CREATE_NEW_BOOK ="INSERT INTO `bookss` (`isbn`, `title`, `genre_id`, `publishing_id`, `year`, `price`) VALUES (?,?,?,?,?,?);";
  private static final String READ_ALL_BOOKS = "SELECT `isbn` as id, title, bookss.genre_id, bookss.publishing_id, `year`, `price`, genre, publishing FROM bookss inner join genre on genre.genre_id = bookss.genre_id inner join publishing on publishing.publishing_id = bookss.publishing_id;";
  private static final String READ_BOOK_BY_ID = "SELECT `isbn` as id, title, bookss.genre_id, bookss.publishing_id, `year`, `price`, genre, publishing FROM bookss inner join genre on genre.genre_id = bookss.genre_id inner join publishing on publishing.publishing_id = bookss.publishing_id WHERE bookss.`isbn` = (?);";
  private static final String UPDATE_BOOK_BY_ID = "UPDATE bookss SET genre_id = ?, publishing_id = ?, `year` = ?, price = ?, title = ? WHERE isbn = ?;";
  private static final String DELETE_BOOK_BY_ID = "DELETE FROM `bookss` WHERE `isbn` = (?);";

  private final BookBuilder builder;

  public BookDAOImpl() {
    this.builder = new BookBuilder();
  }

  @Override
  public Integer create(Book book) throws DaoException {
    try (PreparedStatement statement = ConnectionPool.getInstance().getConnection()
        .prepareStatement(CREATE_NEW_BOOK)) {
      statement.setInt(1, book.getId());
      statement.setString(2, book.getTitle());
      statement.setInt(3, book.getGenre().getId());
      statement.setInt(4, book.getPublishing().getId());
      statement.setInt(5, book.getYear());
      statement.setFloat(6, book.getPrice());
      statement.execute();
      return book.getId();
    } catch (SQLException | PoolException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Book> readAllBooks() throws DaoException {
    ArrayList<Book> allBooks = new ArrayList<>();
    try (Statement statement = ConnectionPool.getInstance().getConnection().createStatement()) {
      ResultSet query = statement.executeQuery(READ_ALL_BOOKS);
      while (query.next()) {
        allBooks.add(createBookFromQuery(query));
      }
    } catch (SQLException | PoolException e) {
      throw new DaoException(e);
    }
    return allBooks;
  }

  @Override
  public Book read(Integer id) throws DaoException {
    try (PreparedStatement statement = ConnectionPool.getInstance().getConnection()
        .prepareStatement(READ_BOOK_BY_ID)) {
      statement.setInt(1, id);
      ResultSet query = statement.executeQuery();
      if (query.next()) {
        return createBookFromQuery(query);
      }
    } catch (SQLException | PoolException e) {
      throw new DaoException(e);
    }
    return null;
  }

  @Override
  public void update(Book book) throws DaoException {

    try (PreparedStatement statement = ConnectionPool.getInstance().getConnection()
        .prepareStatement(UPDATE_BOOK_BY_ID)) {
      statement.setInt(1, book.getGenre().getId());
      statement.setInt(2, book.getPublishing().getId());
      statement.setInt(3, book.getYear());
      statement.setFloat(4, book.getPrice());
      statement.setString(5, book.getTitle());
      statement.executeUpdate();
    } catch (SQLException | PoolException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void delete(Integer id) throws DaoException {
    try (PreparedStatement statement = ConnectionPool.getInstance().getConnection()
        .prepareStatement(DELETE_BOOK_BY_ID)) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException | PoolException e) {
      throw new DaoException(e);
    }
  }

  private Book createBookFromQuery(ResultSet query) throws SQLException {
    builder.setId(query.getInt(ID));
    builder.setTitle(query.getString(TITLE));
    builder.setGenre(new Genre(query.getInt(GENRE_ID), query.getString(GENRE)));
    builder.setPublishing(new Publishing(query.getInt(PUBLISHING_ID), query.getString(PUBLISHING)));
    builder.setYear(query.getInt(YEAR));
    builder.setPrice(query.getInt(PRICE));
    Book book = builder.getResult();
    builder.reset();

    return book;
  }
}
