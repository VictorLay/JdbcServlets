package com.victor.latyshey.validation;

import com.victor.latyshey.bean.UserSessionInf;

public class SessionInfoValidator implements Validator<UserSessionInf> {

  @Override
  public String isValid(UserSessionInf sessionInf) {
    StringBuilder stringBuilder = new StringBuilder();

    if (sessionInf == null){
      return stringBuilder.append("The session inform is null").toString();
    }
    if (sessionInf.getId()==null){
      stringBuilder.append("The ID of user isn't define.");
    }
    if (sessionInf.getLogin()==null){
      stringBuilder.append("The login of user isn't define.");
    }
    return stringBuilder.toString();
  }
}
