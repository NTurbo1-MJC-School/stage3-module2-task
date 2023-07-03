package com.mjc.school.controller.helper;

public enum Operations {
  GET_ALL_NEWS(1, "Get all news."),
  GET_ALL_AUTHORS(2, "Get all authors"),
  GET_NEWS_BY_ID(3, "Get news by id."),
  GET_AUTHOR_BY_ID(4, "Get author by id."),
  CREATE_NEWS(5, "Create news."),
  CREATE_AUTHOR(6, "Create author."),
  UPDATE_NEWS(7, "Update news."),
  UPDATE_AUTHOR(8, "Update author."),
  REMOVE_NEWS_BY_ID(9, "Remove news by id."),
  REMOVE_AUTHOR_BY_ID(10, "Remove author by id."),
  EXIT(0, "Exit.");

  private final Integer operationNumber;
  private final String operation;

  Operations(Integer operationNumber, String operation) {
    this.operationNumber = operationNumber;
    this.operation = operation;
  }

  public String getOperation() {
    return Constant.OPERATION + operation;
  }

  public String getOperationWithNumber() {
    return operationNumber + " - " + operation;
  }
}
