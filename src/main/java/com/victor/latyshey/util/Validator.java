package com.victor.latyshey.util;

import com.victor.latyshey.bean.UserSessionInf;
import com.victor.latyshey.service.UserService;
import com.victor.latyshey.service.exception.ServiceException;
import com.victor.latyshey.validation.SessionInfoValidator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;

public class Validator {

  private static final String LOGIN = "login";
  private static final String ROLE = "role";
  private static final String SESSION_USER_INFO = "user";


  public static boolean ValidationUserName(String userName) {
    String expression = "([0-9])+";
    Pattern pattern = Pattern.compile(expression);
    Matcher matcher = pattern.matcher(userName);
    return matcher.matches();
  }

  public static boolean isAuthorized(HttpServletRequest req, UserService userService)
      throws ServiceException {
    if (req.getSession() == null) {
      return false;
    }

    UserSessionInf user = (UserSessionInf) req.getSession().getAttribute(SESSION_USER_INFO);
    SessionInfoValidator validator = new SessionInfoValidator();
    if (validator.isValid(user) && user.getLogin().equals(req.getSession().getAttribute(LOGIN))
        && user.getRole().equals(req.getSession().getAttribute(ROLE))) {
      return userService.isUserExist(user);
    } else {
      return false;
    }
  }
}
