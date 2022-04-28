package com.victor.latyshey.dao;

import com.victor.latyshey.beans.Customer;

public interface CustomerDAO {

  void createCustomer(Customer customer);

  Customer findCustomer(Customer customer);

}
