package com.victor.latyshey.controller;

import com.victor.latyshey.connection.ConnectionPool;
import com.victor.latyshey.controller.command.Command;
import com.victor.latyshey.controller.command.CommandResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ResourceManager;

public class ServletController extends HttpServlet {

  @Override
  public void destroy() {
    ConnectionPool.getInstance().destroy();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("hello it's get request");
    processRequest(req, resp, "GET");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("hello it's post request");
    processRequest(req, resp, "POST");
    try {
      req.setCharacterEncoding("UTF-8");
    } catch (UnsupportedEncodingException e) {
      System.out.println("encoding wasn't set");
      throw new RuntimeException(e);
    }


  }

  private void processRequest(HttpServletRequest req, HttpServletResponse resp, String requestType)
      throws ServletException, IOException {

    String commandName = req.getParameter("command");
//    System.out.println(commandName);
    Command command = CommandProvider.getCommand(commandName);
    if (command == null) {
      getServletContext().getRequestDispatcher(ResourceManager.getProperty("page.home"))
          .forward(req, resp);
    } else {
      System.out.println(commandName);
      CommandResponse commandResponse = command.execute(req, resp);

      if (commandResponse.isRedirect()) {
        redirect(resp, commandResponse.getResponsePage());
      } else {
        forward(req, resp, commandResponse.getResponsePage());
      }

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
