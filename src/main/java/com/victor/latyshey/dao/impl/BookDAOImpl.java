package com.victor.latyshey.dao.impl;

import com.victor.latyshey.bean.book.Author;
import com.victor.latyshey.bean.book.Book;
import com.victor.latyshey.bean.book.Genre;
import com.victor.latyshey.bean.book.Publishing;
import com.victor.latyshey.bean.builder.BookBuilder;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.exception.DaoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookDAOImpl extends DaoConnection implements BookDAO {

  public static final String ID = "id";
  public static final String TITLE = "title";
  public static final String GENRE_ID = "genre_id";
  public static final String GENRE = "genre";
  public static final String PUBLISHING_ID = "publishing_id";
  public static final String PUBLISHING = "publishing";
  public static final String YEAR = "year";
  public static final String PRICE = "price";
  private static final String CREATE_NEW_BOOK = "INSERT INTO `books` (`isbn`, `title`, `genre_id`, `publishing_id`, `year`, `price`) VALUES (?,?,?,?,?,?);";
  private static final String READ_ALL_BOOKS = "SELECT books.`isbn` as id, title, books.genre_id, books.publishing_id, `year`, `price`, genre, publishing, group_concat(authors.author_name) as `authors` FROM books inner join genre on genre.genre_id = books.genre_id inner join publishing on publishing.publishing_id = books.publishing_id inner join books_authors on books.isbn = books_authors.isbn inner join authors on authors.author_id = books_authors.author_id GROUP BY books.`isbn`;";
  private static final String READ_BOOK_BY_ID = "SELECT books.`isbn` as id, title, books.genre_id, books.publishing_id, `year`, `price`, genre, publishing, group_concat(authors.author_name) as `authors` FROM books inner join genre on genre.genre_id = books.genre_id inner join publishing on publishing.publishing_id = books.publishing_id inner join books_authors on books.isbn = books_authors.isbn inner join authors on authors.author_id = books_authors.author_id WHERE books.`isbn` = (?);";
  private static final String UPDATE = "UPDATE books SET genre_id = ?, publishing_id = ?, `year` = ?, price = ?, title = ? WHERE isbn = ?;";
  private static final String DELETE_BOOK_AUTHORS_LINK_BY_ISBN = "DELETE FROM `books_authors` WHERE isbn=(?);";

  private static final String DELETE = "DELETE FROM `books` WHERE `isbn` = (?);";
  private static final String CREATE_BOOK_AUTHORS_LINK = "INSERT INTO books_authors (isbn, author_id) VALUES (?,?);";

  private final BookBuilder builder;

  public BookDAOImpl() {
    this.builder = new BookBuilder();
  }

  @Override
  public Integer create(Book book) throws DaoException {
    try (PreparedStatement statement = connection.get().prepareStatement(CREATE_NEW_BOOK);
        PreparedStatement statementForLinkingTable = connection.get().prepareStatement(
            CREATE_BOOK_AUTHORS_LINK)) {
      statement.setInt(1, book.getId());
      statement.setString(2, book.getTitle());
      statement.setInt(3, book.getGenre().getId());
      statement.setInt(4, book.getPublishing().getId());
      statement.setInt(5, book.getYear());
      statement.setFloat(6, book.getPrice());
      statement.execute();
      if (!book.getAuthors().isEmpty()) {
        for (Author author : book.getAuthors()) {
          statementForLinkingTable.setInt(1, book.getId());
          statementForLinkingTable.setInt(2, author.getId());
          statementForLinkingTable.executeUpdate();
        }
      }
      return book.getId();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Book> readAllBooks() throws DaoException {
    ArrayList<Book> allBooks = new ArrayList<>();
    try (PreparedStatement statement = connection.get().prepareStatement(READ_ALL_BOOKS)) {
      ResultSet query = statement.executeQuery();
      while (query.next()) {
        allBooks.add(createBookFromQuery(query));
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    }
    return allBooks;
  }

  @Override
  public Book read(Integer id) throws DaoException {
    try (PreparedStatement statement = connection.get().prepareStatement(READ_BOOK_BY_ID)) {
      statement.setInt(1, id);
      ResultSet query = statement.executeQuery();
      if (query.next()) {
        return createBookFromQuery(query);
      } else {
        return null;
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Book book) throws DaoException {

    try (PreparedStatement statement = connection.get().prepareStatement(UPDATE);
        PreparedStatement statement1 = connection.get().prepareStatement(DELETE_BOOK_AUTHORS_LINK_BY_ISBN);
    PreparedStatement statementForLinkingTable = connection.get().prepareStatement(CREATE_BOOK_AUTHORS_LINK)) {
      statement.setInt(1, book.getGenre().getId());
      statement.setInt(2, book.getPublishing().getId());
      statement.setInt(3, book.getYear());
      statement.setFloat(4, book.getPrice());
      statement.setString(5, book.getTitle());
      statement.setInt(6, book.getId());
      statement.executeUpdate();

      statement1.setInt(1, book.getId());
      statement1.executeUpdate();

      if (!book.getAuthors().isEmpty()) {
        for (Author author : book.getAuthors()) {
          statementForLinkingTable.setInt(1, book.getId());
          statementForLinkingTable.setInt(2, author.getId());
          statementForLinkingTable.executeUpdate();
        }
      }

    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void delete(Integer id) throws DaoException {
    try (PreparedStatement statement = connection.get().prepareStatement(DELETE);
        PreparedStatement authorStatement = connection.get()
            .prepareStatement(DELETE_BOOK_AUTHORS_LINK_BY_ISBN)) {
      statement.setInt(1, id);
      statement.executeUpdate();

      authorStatement.setInt(1, id);
      authorStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  private Book createBookFromQuery(ResultSet query) throws SQLException {
    builder.setId(query.getInt(ID));
    builder.setTitle(query.getString(TITLE));
    builder.setGenre(new Genre(query.getInt(GENRE_ID), query.getString(GENRE)));
    builder.setPublishing(new Publishing(query.getInt(PUBLISHING_ID), query.getString(PUBLISHING)));
    builder.setYear(query.getInt(YEAR));
    builder.setPrice(query.getFloat(PRICE));

    builder.setAuthors(Arrays.stream(query.getString("authors").split(","))
        .map(x -> new Author(x, null)).collect(Collectors.toList()));

    Book book = builder.getResult();
    builder.reset();

    return book;
  }
}
