package com.victor.latyshey.dao.impl;

import com.victor.latyshey.bean.user.Role;
import com.victor.latyshey.bean.user.User;
import com.victor.latyshey.dao.UserDAO;
import com.victor.latyshey.dao.exception.DaoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

public class UserDaoImpl extends DaoConnection implements UserDAO {

  private static final String READ_USER_BY_LOGIN_AND_PASSWORD = "SELECT `user_id` as id, `role`, `login`, `users`.`role_id` FROM mydbtest.users inner join mydbtest.roles ON users.role_id = roles.role_id WHERE `login` = ? and `password` = ?;";
  private static final String READ_USER_BY_ID = "SELECT `login`, `role`, `user_id` as `id`, `users`.`role_id` FROM `users` INNER JOIN `roles` ON `users`.`role_id` = `roles`.`role_id` WHERE `user_id` = ?;";
  private static final String CREATE_USER = "INSERT INTO `users` (`role_id`, `login`, `password`) VALUES (?,?,?);";
  private static final String UPDATE_USER = "UPDATE `users` SET login = ?, `role_id` = ? where user_id = ?;";
  private static final String DELETE_USER = "DELETE FROM `users` WHERE `user_id` = (?)";

  private static final String LOGIN = "login";
  private static final String ROLE = "role";
  private static final String USER_ID = "id";
  private static final String ROLE_ID = "role_id";
  private static final String ILLEGAL_LOGIN_AND_PASSWORD = "User with such login and password isn't exist.";

  @Override
  public Integer create(User user) throws DaoException {
    try (PreparedStatement statement = connection.get().prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
      statement.setInt(1, user.getRole().getId());
      statement.setString(2, user.getLogin());
      statement.setInt(3, user.getPassword().hashCode());

      statement.executeUpdate();
      ResultSet generatedKeys = statement.getGeneratedKeys();
      generatedKeys.next();
      return generatedKeys.getInt(1);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public User read(String signLogin, String password) throws DaoException {
    try (PreparedStatement statement = connection.get().prepareStatement(READ_USER_BY_LOGIN_AND_PASSWORD)) {
      statement.setString(1, signLogin);
      statement.setInt(2, password.hashCode());
      ResultSet query = statement.executeQuery();

      if (query.next()) {
        return new User(query.getString(LOGIN),
            Role.of(query.getString(ROLE)).get(), query.getInt(USER_ID));
      } else {
        throw new DaoException(ILLEGAL_LOGIN_AND_PASSWORD);
      }
    } catch (NoSuchElementException | SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public User read(Integer id) throws DaoException {
    try (PreparedStatement statement = connection.get().prepareStatement(READ_USER_BY_ID)) {
      statement.setInt(1, id);
      ResultSet query = statement.executeQuery();

      if (query.next()) {
        return new User(query.getString(LOGIN), Role.of(query.getString(ROLE)).get(),
            query.getInt(USER_ID));
      }
      return null;
    } catch (NoSuchElementException | SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(User user) throws DaoException {
    try (PreparedStatement statement = connection.get().prepareStatement(UPDATE_USER)) {
      statement.setString(1, user.getLogin());
      statement.setInt(2, user.getRole().getId());
      statement.setInt(3, user.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void delete(Integer id) throws DaoException {
    try (PreparedStatement statement = connection.get().prepareStatement(DELETE_USER)) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }
}
