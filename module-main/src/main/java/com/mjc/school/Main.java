package com.mjc.school;

import static com.mjc.school.controller.helper.Constant.COMMAND_NOT_FOUND;

import java.util.Scanner;

import com.mjc.school.config.ApplicationConfig;
import com.mjc.school.controller.command.CommandReceiver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    Scanner keyboard = new Scanner(System.in);
    CommandReceiver commandReceiver = context.getBean(CommandReceiver.class);

    while (true) {
      commandReceiver.printMainMenu();
      String command = keyboard.nextLine();

      switch (command) {
        case "1" -> commandReceiver.getAllNews();
        case "2" -> commandReceiver.getAllAuthors();
        case "3" -> commandReceiver.getNewsById();
        case "4" -> commandReceiver.getAuthorById();
        case "5" -> commandReceiver.createNews();
        case "6" -> commandReceiver.createAuthor();
        case "7" -> commandReceiver.updateNews();
        case "8" -> commandReceiver.updateAuthor();
        case "9" -> commandReceiver.deleteNews();
        case "10" -> commandReceiver.deleteAuthor();
        case "0" -> commandReceiver.exit();
        default -> System.out.println(COMMAND_NOT_FOUND);
      }
    }
  }
}
