package com.mjc.school.controller.command.implementation.create;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateNewsCommand implements Command {

    private BaseController newsController;
    private Scanner keyboard;

    @Autowired
    public CreateNewsCommand(@Qualifier("newsController") BaseController newsController) {
        this.newsController = newsController;
        this.keyboard = new Scanner(System.in);
    }
    @Override
    public void execute() {
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
                Long authorId = CommandHelper.getKeyboardNumber(Constant.AUTHOR_ID, keyboard);
                dtoRequest = new NewsDtoRequest(null, title, content, authorId);
                isValid = true;

                System.out.println(CommandHelper.findCommandHandlerFor("5", newsController).invoke(newsController, dtoRequest));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
