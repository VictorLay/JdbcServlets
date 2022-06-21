package com.victor.latyshey.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Command {

  CommandResponse execute(HttpServletRequest req, HttpServletResponse resp);

  Logger logger = LogManager.getLogger();

}
