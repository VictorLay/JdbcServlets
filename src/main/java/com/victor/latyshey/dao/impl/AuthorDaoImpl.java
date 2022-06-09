package com.victor.latyshey.dao.impl;

import com.victor.latyshey.bean.book.Author;
import com.victor.latyshey.dao.AuthorDao;
import com.victor.latyshey.dao.exception.DaoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl extends DaoConnection implements AuthorDao {

  public static final String ID = "author_id";
  public static final String NAME = "author_name";
  public static final String COUNTRY = "author_country";
  private static final String CREATE_AUTHOR = "INSERT INTO `authors` (author_name, author_country) VALUES (?,?)";
  private static final String READ_AUTHOR_BY_ID = "SELECT * FROM authors WHERE author_id = (?)";
  private static final String READ_AUTHOR_BY_NAME = "SELECT * FROM authors WHERE author_name = (?)";
  private static final String READ_AUTHOR_BY_COUNTRY = "SELECT * FROM authors WHERE author_country = (?)";
  private static final String UPDATE_AUTHOR = "UPDATE authors SET author_name = (?), author_country = (?) WHERE author_id = (?);";
  private static final String DELETE_AUTHOR = "DELETE FROM authors WHERE author_id = (?)";

  @Override
  public Integer create(Author author) throws DaoException {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_AUTHOR, Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, author.getName());
      statement.setString(2, author.getCountry());
      statement.executeUpdate();
      ResultSet generatedId = statement.getGeneratedKeys();
      generatedId.next();
      return generatedId.getInt(1);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Author read(Integer id) throws DaoException {
    try (PreparedStatement statement = connection.prepareStatement(READ_AUTHOR_BY_ID)) {
      statement.setInt(1, id);
      ResultSet query = statement.executeQuery();
      query.next();
      return new Author(query.getInt(ID), query.getString(NAME), query.getString(COUNTRY));
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Author readByName(String authorName) throws DaoException {
    try (PreparedStatement statement = connection.prepareStatement(READ_AUTHOR_BY_NAME)) {
      statement.setString(1, authorName);
      ResultSet query = statement.executeQuery();
      if (query.next()) {
        return new Author(query.getInt(ID), query.getString(NAME), query.getString(COUNTRY));
      }
      return null;
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Author> readByCountry(String country) throws DaoException {
    try (PreparedStatement statement = connection.prepareStatement(READ_AUTHOR_BY_COUNTRY)) {
      List<Author> authorList = new ArrayList<>();
      statement.setString(1, country);
      ResultSet query = statement.executeQuery();
      while (query.next()) {
        authorList.add(
            new Author(query.getInt(ID), query.getString(NAME), query.getString(COUNTRY)));
      }
      return authorList;
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Author author) throws DaoException {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR)) {
      statement.setString(1, author.getName());
      statement.setString(2, author.getCountry());
      statement.setInt(3, author.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void delete(Integer id) throws DaoException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_AUTHOR)) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }
}
