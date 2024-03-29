package com.victor.latyshey.controller;

import com.victor.latyshey.dao.connection.ConnectionPool;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServletController extends HttpServlet {

  private final Logger log = LogManager.getLogger();

  @Override
  public void destroy() {
    ConnectionPool.getInstance().destroy();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    processRequest(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    processRequest(req, resp);
  }

  private void processRequest(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String commandName = req.getParameter("command");
    Command command = CommandProvider.getCommand(commandName);
    CommandResponse commandResponse = command.execute(req, resp);

    if (commandResponse.isRedirect()) {
      redirect(resp, commandResponse.getResponsePage());
    } else {
      forward(req, resp, commandResponse.getResponsePage());
    }
  }

  private void forward(HttpServletRequest request, HttpServletResponse response, String page)
      throws ServletException, IOException {
    System.out.println(page);
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + page);
    dispatcher.forward(request, response);
  }

  private void redirect(HttpServletResponse response, String page) throws IOException {
    response.sendRedirect(page);
  }

}
