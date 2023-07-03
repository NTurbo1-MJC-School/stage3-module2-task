package com.mjc.school.controller.helper;

import java.lang.reflect.Method;
import java.util.Scanner;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.exceptions.CommandHandlerNotFoundException;
import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatorException;

public class CommandHelper {

  public void printMainMenu() {
    System.out.println(Constant.NUMBER_OPERATION_ENTER);
    for (Operations operation : Operations.values()) {
      System.out.println(operation.getOperationWithNumber());
    }
  }

  public void getNews(NewsController newsController) {
    System.out.println(Operations.GET_ALL_NEWS.getOperation());
    newsController.readAll().forEach(System.out::println);
  }

  public void getNewsById(NewsController newsController, Scanner keyboard) {
    System.out.println(Operations.GET_NEWS_BY_ID.getOperation());
    System.out.println(Constant.NEWS_ID_ENTER);
    System.out.println(newsController.readById(getKeyboardNumber(Constant.NEWS_ID, keyboard)));
  }

  public void createNews(NewsController newsController, Scanner keyboard) {
    NewsDtoRequest dtoRequest = null;
    boolean isValid = false;
    while (!isValid) {
      try {
        System.out.println(Operations.CREATE_NEWS.getOperation());
        System.out.println(Constant.NEWS_TITLE_ENTER);
        String title = keyboard.nextLine();
        System.out.println(Constant.NEWS_CONTENT_ENTER);
        String content = keyboard.nextLine();
        System.out.println(Constant.AUTHOR_ID_ENTER);
        Long authorId = getKeyboardNumber(Constant.AUTHOR_ID, keyboard);
        dtoRequest = new NewsDtoRequest(null, title, content, authorId);
        isValid = true;
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    System.out.println(newsController.create(dtoRequest));
  }

  public void updateNews(NewsController newsController, Scanner keyboard) {
    NewsDtoRequest dtoRequest = null;
    boolean isValid = false;
    while (!isValid) {
      try {
        System.out.println(Operations.UPDATE_NEWS.getOperation());
        System.out.println(Constant.NEWS_ID_ENTER);
        Long newsId = getKeyboardNumber(Constant.NEWS_ID, keyboard);
        System.out.println(Constant.NEWS_TITLE_ENTER);
        String title = keyboard.nextLine();
        System.out.println(Constant.NEWS_CONTENT_ENTER);
        String content = keyboard.nextLine();
        System.out.println(Constant.AUTHOR_ID_ENTER);
        Long authorId = getKeyboardNumber(Constant.AUTHOR_ID, keyboard);
        dtoRequest = new NewsDtoRequest(newsId, title, content, authorId);
        isValid = true;
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    System.out.println(newsController.update(dtoRequest));
  }

  public void deleteNews(NewsController newsController, Scanner keyboard) {
    System.out.println(Operations.REMOVE_NEWS_BY_ID.getOperation());
    System.out.println(Constant.NEWS_ID_ENTER);
    System.out.println(newsController.deleteById(getKeyboardNumber(Constant.NEWS_ID, keyboard)));
  }

  public static long getKeyboardNumber(String params, Scanner keyboard) {
    long enter;
    try {
      enter = keyboard.nextLong();
      keyboard.nextLine();
    } catch (Exception ex) {
      keyboard.nextLine();
      throw new ValidatorException(
          String.format(ServiceErrorCode.VALIDATE_INT_VALUE.getMessage(), params));
    }
    return enter;
  }

  public static Method findCommandHandlerFor(String command, BaseController controller) {
    for (Method method : controller.getClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(CommandHandler.class)) {
        if (method.getAnnotation(CommandHandler.class).value().equals(command)) {
          return method;
        }
      }
    }

    throw new CommandHandlerNotFoundException("No command handler to handle the command '" +
                                              command + "'.");
  }
}
