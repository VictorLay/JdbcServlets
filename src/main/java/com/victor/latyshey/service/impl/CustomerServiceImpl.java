package com.victor.latyshey.service.impl;

import com.victor.latyshey.beans.Customer;
import com.victor.latyshey.dao.CustomerDAO;
import com.victor.latyshey.dao.factory.DaoFactory;
import com.victor.latyshey.service.CustomerService;
import com.victor.latyshey.validation.CustomerValidator;
import com.victor.latyshey.validation.Validator;

public class CustomerServiceImpl implements CustomerService {

  @Override
  public void createCustomer(Customer customer) {
    Validator<Customer> customerValidator = new CustomerValidator();
    String errorMessage = customerValidator.isValid(customer);

    if ( errorMessage != null){
      System.out.println(errorMessage);
    }else {
      DaoFactory daoFactory = DaoFactory.getInstance();
      CustomerDAO customerDAO = daoFactory.getCustomerDAO();
      customerDAO.createCustomer(customer);

    }


  }
}
