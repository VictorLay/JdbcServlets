package com.victor.latyshey.service;

import com.victor.latyshey.dao.transaction.Transaction;
import com.victor.latyshey.service.exception.ServiceException;

public interface Service {
  Transaction getTransaction() throws ServiceException;
  void releaseTheConnection();
}
