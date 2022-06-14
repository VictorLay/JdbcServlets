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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EncodingFilter implements Filter {

  private FilterConfig config;
  private boolean active;
  private final Logger logger = LogManager.getLogger(EncodingFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    config = filterConfig;
    String active = config.getInitParameter("active");
    if (active != null) {
      this.active = active.toUpperCase(Locale.ROOT).equals("TRUE");
    }
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    if (active) {
      HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      httpServletRequest.setCharacterEncoding("UTF-8");
      filterChain.doFilter(servletRequest, servletResponse);
      logger.log(Level.INFO, "The filter is used.");
    } else {
      logger.log(Level.INFO, "The filter isn't used.");
    }
  }
}
