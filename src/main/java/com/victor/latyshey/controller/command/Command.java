package com.victor.latyshey.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

  CommandResponse execute(HttpServletRequest req, HttpServletResponse resp);

}
