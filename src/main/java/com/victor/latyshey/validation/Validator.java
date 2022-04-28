package com.victor.latyshey.validation;

public interface Validator<T>{

  String isValid(T instance);

}
