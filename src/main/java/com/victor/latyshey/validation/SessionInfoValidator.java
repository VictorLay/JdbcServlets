package com.victor.latyshey.validation;

import com.victor.latyshey.bean.UserSessionInf;

public class SessionInfoValidator implements Validator<UserSessionInf> {

  @Override
  public boolean isValid(UserSessionInf sessionInf) {
    return sessionInf != null && sessionInf.getId() != null && sessionInf.getLogin() != null;
  }

}
