package com.victor.latyshey.controller.command.impl;

import com.victor.latyshey.beans.Book;
import com.victor.latyshey.beans.Customer;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.BookDAO;
import com.victor.latyshey.dao.CustomerDAO;
import com.victor.latyshey.dao.factory.DaoFactory;
import com.victor.latyshey.service.CustomerService;
import com.victor.latyshey.service.impl.CustomerServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ResourceManager;

public class Registration implements Command {

  private final static String URL = "jdbc:mysql://localhost:3300/mydbtest";
  private final static String USERNAME = "root";
  private final static String PASSWORD = "root";

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    System.out.println("The command was execute!");

//    try {
//      //todo без  Class.forName не определяет драйвер.
//      // В чём заключалась проблема и как команда помогла её решить
//      Class.forName("com.mysql.cj.jdbc.Driver");
//      Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//      connection.close();
//    } catch (SQLException e) {
//      System.out.println("Problem");
//      throw new RuntimeException(e);
//    } catch (ClassNotFoundException e) {
//      System.out.println("Problem");
//      throw new RuntimeException(e);
//    }

    DaoFactory daoFactory = DaoFactory.getInstance();
    CustomerDAO customerDAO = daoFactory.getCustomerDAO();
    String name = String.valueOf(req.getParameter("name"));
    String lastname = String.valueOf(req.getParameter("lastname"));
    String login = String.valueOf(req.getParameter("login"));
//    System.out.println(login);
    String password = String.valueOf(req.getParameter("password"));

//    System.out.println(name + lastname + login + password);
//    customerDAO.createCustomer(new Customer(name, lastname, login, password));
    CustomerService customerService = new CustomerServiceImpl();
    customerService.createCustomer(new Customer(name, lastname, login, password));
    return new CommandResponse(ResourceManager.getProperty("page.home"),false);
  }
}
