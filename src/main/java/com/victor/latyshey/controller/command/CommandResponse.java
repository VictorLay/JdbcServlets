package com.victor.latyshey.controller.command;

public class CommandResponse {

  private String responsePage;
  private boolean isRedirect;

  public CommandResponse(String responsePage, boolean isRedirect) {
    this.responsePage = responsePage;
    this.isRedirect = isRedirect;
  }

  public String getResponsePage() {
    return responsePage;
  }

  public boolean isRedirect() {
    return isRedirect;
  }
}
