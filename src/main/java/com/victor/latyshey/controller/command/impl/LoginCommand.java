package com.victor.latyshey.controller.command.impl;

import com.victor.latyshey.beans.Customer;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import com.victor.latyshey.dao.CustomerDAO;
import com.victor.latyshey.dao.factory.DaoFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ResourceManager;

public class LoginCommand implements Command {

  @Override
  public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    System.out.println(login + " " + password);
    DaoFactory daoFactory = DaoFactory.getInstance();
    CustomerDAO customerDAO = daoFactory.getCustomerDAO();
    Customer customer = customerDAO.findCustomer(new Customer(null, null, login, password));
    System.out.println(customer);
    return new CommandResponse(ResourceManager.getProperty("page.customer"), false);
  }
}
