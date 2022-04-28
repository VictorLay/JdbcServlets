package com.victor.latyshey.validation;

import com.victor.latyshey.beans.Customer;

public class CustomerValidator implements Validator<Customer> {

  @Override
  public String isValid(Customer instance) {
    if (instance.getName() == null) {
      return "Name is null";
    }

    if (instance.getLastname() == null) {
      return "Lastname is null";
    }

    if (instance.getPassword() == null) {
      return "Password is null";
    }
    if (instance.getLogin() == null) {
      return "Login is null";
    }
    return null;
  }
}
