package com.victor.latyshey.validation;

public interface Validator<T>{

  boolean isValid(T instance);

}
