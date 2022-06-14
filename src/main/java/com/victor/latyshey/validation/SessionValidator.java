package com.victor.latyshey.validation;

import javax.servlet.http.HttpServletRequest;

public class SessionValidator implements Validator<HttpServletRequest> {

  @Override
  public boolean isValid(HttpServletRequest instance) {
    return instance.getSession() != null;
  }
}
