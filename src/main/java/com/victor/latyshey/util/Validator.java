package com.victor.latyshey.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean ValidationUserName(String userName){
        String expression = "([0-9])+";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }
}
