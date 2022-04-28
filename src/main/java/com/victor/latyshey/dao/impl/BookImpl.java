package com.victor.latyshey.dao.impl;

import com.victor.latyshey.beans.Book;
import com.victor.latyshey.dao.BookDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookImpl implements BookDAO {

  private final static String URL = "jdbc:mysql://localhost:3300/mydbtest";
  private final static String USERNAME = "root";
  private final static String PASSWORD = "root";
  private final static String UPDATE_BOOK_BY_ISBN =
      "UPDATE books SET `title`=(?),`genre`=(?),`publishing`=(?),`year`=(?),"
          + "`book_binding`=(?),`book_description`=(?),`price`=(?)  WHERE `isbn`=";
  private final static String CREATE_NEW_BOOK =
      "INSERT INTO books (ISBN, title, genre, publishing, year, book_binding, book_description, "
          + "price) VALUES (?,?,?,?,?,?,?,?)";
  private final static String DELETE_BOOK_BY_ISBN = "DELETE FROM `books` WHERE `ISBN`=(?)";

  @Override
  public boolean createBook(Book book) {
    try {
      Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      PreparedStatement statement = con.prepareStatement(CREATE_NEW_BOOK);

      statement.setInt(1, book.getIsbn());
      statement.setString(2, book.getTitle());
      statement.setString(3, book.getGenre());
      statement.setString(4, book.getPublishing());
      statement.setInt(5, book.getYear());
      statement.setString(6, book.getBookBinding());
      statement.setString(7, book.getBookDescription());
      statement.setFloat(8, book.getPrice());
      statement.execute();

      statement = con.prepareStatement("INSERT INTO `authors` (`author`,`ISBN`) VALUES (?,?)");
      for (String author : book.getAuthors()) {
        statement.setString(1, author);
        statement.setInt(2, book.getIsbn());
        statement.execute();
        statement.clearParameters();
        System.out.println(book.getIsbn());
      }

      statement.close();
      con.close();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public ArrayList<Book> readAllBooks() {
    ArrayList<Book> allBooks = new ArrayList<>();

    Connection con = null;
    try {
      con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      PreparedStatement statement = con.prepareStatement("SELECT * FROM books");
      ResultSet executeQuery = statement.executeQuery();

      while (executeQuery.next()) {
        Book book = new Book();
        book.setIsbn(executeQuery.getInt("ISBN"));
        book.setTitle(executeQuery.getNString("title"));
        book.setGenre(executeQuery.getNString("genre"));
        book.setPublishing(executeQuery.getNString("publishing"));
        book.setYear(executeQuery.getInt("year"));
        book.setBookBinding(executeQuery.getNString("book_binding"));
        book.setBookDescription(executeQuery.getNString("book_description"));
        book.setPrice(executeQuery.getFloat("price"));
        allBooks.add(book);
      }
      statement = con.prepareStatement("Select `ISBN`,`author` FROM `authors` WHERE `isbn` = (?)");
      for (Book book : allBooks) {
        statement.setInt(1, book.getIsbn());
        executeQuery = statement.executeQuery();
        StringBuffer stringBuffer = new StringBuffer();
        while (executeQuery.next()) {
          stringBuffer.append(executeQuery.getNString("author") + ";");
        }
        book.setAuthors(stringBuffer.toString().split(";"));
        statement.clearParameters();

      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return allBooks;
  }

  @Override
  public Book readBookByIsbn(int isbn) {
    Book book = null;
    try {
      Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      PreparedStatement statement = con.prepareStatement(
          "SELECT * FROM books WHERE `isbn`=" + isbn);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        book = new Book(resultSet.getInt("ISBN"), resultSet.getNString("title"),
            resultSet.getNString("genre"), resultSet.getNString("publishing"),
            resultSet.getInt("year"), resultSet.getNString("book_binding"),
            resultSet.getNString("book_description"), resultSet.getFloat("price"));

      }
      statement = con.prepareStatement("select author from authors where `ISBN` = (?)");
      statement.setInt(1, isbn);
      resultSet = statement.executeQuery();
      StringBuffer stringBuffer = new StringBuffer();
      while (resultSet.next()) {
        stringBuffer.append(resultSet.getNString("author") + ";");
      }
      book.setAuthors(stringBuffer.toString().split(";"));
      statement.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return book;
  }

  @Override
  public ArrayList<Book> readBooksByPrice(int min, int max) {
    return null;
  }

  @Override
  public ArrayList<Book> readBooksByTitle(String title) {
    ArrayList<Book> books = new ArrayList<>();
    try {
      Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//      PreparedStatement statement = connection.prepareStatement(
//          "SELECT * FROM `books` WHERE `title`=(?)");
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM `books` WHERE `title` LIKE (?)");
      statement.setString(1, title);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        books.add(new Book(resultSet.getInt("ISBN"), resultSet.getNString("title"),
            resultSet.getNString("genre"), resultSet.getNString("publishing"),
            resultSet.getInt("year"), resultSet.getNString("book_binding"),
            resultSet.getNString("book_description"), resultSet.getFloat("price")));
      }

      statement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return books;
  }

  @Override
  public void updateBook(Book book) {
    try {
      Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      PreparedStatement statement = con.prepareStatement(UPDATE_BOOK_BY_ISBN + book.getIsbn());
      statement.setString(1, book.getTitle());
      statement.setString(2, book.getGenre());
      statement.setString(3, book.getPublishing());
      statement.setInt(4, book.getYear());
      statement.setString(5, book.getBookBinding());
      statement.setString(6, book.getBookDescription());
      statement.setFloat(7, book.getPrice());
      statement.execute();

      statement.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean deleteBookByIsbn(int isbn) {
    Book book = null;
    try {
      Connection con;
      PreparedStatement statement;

      con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      statement = con.prepareStatement(DELETE_BOOK_BY_ISBN);
      statement.setInt(1, isbn);
      statement.execute();
      statement.clearParameters();

      statement = con.prepareStatement("DELETE FROM `authors` Where `ISBN` = (?)");
      statement.setInt(1, isbn);
      statement.execute();

      statement.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
