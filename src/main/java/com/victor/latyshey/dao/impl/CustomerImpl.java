package com.victor.latyshey.dao.impl;

import com.victor.latyshey.beans.Book;
import com.victor.latyshey.beans.Customer;
import com.victor.latyshey.connection.ConnectionPool;
import com.victor.latyshey.connection.PoolException;
import com.victor.latyshey.dao.CustomerDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerImpl implements CustomerDAO {

  private final static String URL = "jdbc:mysql://localhost:3300/mydbtest";
  private final static String USERNAME = "root";
  private final static String PASSWORD = "root";

  @Override
  public void createCustomer(Customer customer) {
    try (Connection connection = ConnectionPool.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO `customers` (`login`, `password`, `name`, `lastname`) VALUE (?,?,?,?);");
      statement.setString(1, customer.getLogin());
      statement.setInt(2, customer.getPassword().hashCode());
      statement.setString(3, customer.getName());
      statement.setString(4, customer.getLastname());
      statement.execute();
      statement.close();
    } catch (SQLException | PoolException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Customer findCustomer(Customer customer) {
    Customer responseCustomer = null;
    try (Connection connection = ConnectionPool.getInstance().getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM `customers` WHERE `login` = ? and `password`=?;");
      statement.setString(1, customer.getLogin());
      statement.setInt(2, customer.getPassword().hashCode());
      ResultSet resultOfQuery = statement.executeQuery();

      while (resultOfQuery.next()) {
        String name = resultOfQuery.getString("name");
        String lastname = resultOfQuery.getString("lastname");
        String login = resultOfQuery.getString("login");
//      int password = resultOfQuery.getInt("password");
        int custID = resultOfQuery.getInt("customerID");
        responseCustomer = new Customer(name, lastname, login, custID);
      }


      statement.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (PoolException e) {
      throw new RuntimeException(e);
    }

//    try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//      PreparedStatement statement = connection.prepareStatement(
//          "SELECT * FROM `customers` WHERE `login` = (?) and `password`=(?);");
//      statement.setString(1,customer.getLogin());
//      statement.setInt(2,customer.getPassword().hashCode());
//      ResultSet resultOfQuery = statement.executeQuery();
//      String name = resultOfQuery.getString("name");
//      String lastname = resultOfQuery.getString("lastname");
//      String login = resultOfQuery.getString("login");
////      int password = resultOfQuery.getInt("password");
//      int custID = resultOfQuery.getInt("customerID");
//      responseCustomer = new Customer(name, lastname, login, custID);
//      statement.close();
//
//    } catch (SQLException | PoolException e) {
//      //todo добавить исключение и/или его обработчик!
//      throw new RuntimeException(e);
//    }

    return responseCustomer;
  }

}
