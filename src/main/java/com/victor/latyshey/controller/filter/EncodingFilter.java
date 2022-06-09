package com.victor.latyshey.controller.filter;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

  private FilterConfig config;
  private boolean active;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    config = filterConfig;
    String active = config.getInitParameter("active");
    if (active != null){
      this.active = active.toUpperCase(Locale.ROOT).equals("TRUE");
    }
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    if(active) {
      HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      httpServletRequest.setCharacterEncoding("UTF-8");
      filterChain.doFilter(servletRequest, servletResponse);
      System.out.println("Filter has worked");
    }else{
      System.out.println("The filter isn't turn on");
    }
  }
}
