package com.mjc.school.controller.command.implementation.getById;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class GetNewsByIdCommand implements Command {

    private BaseController newsController;
    private Scanner keyboard;

    @Autowired
    public GetNewsByIdCommand(@Qualifier("keyboardScanner") Scanner keyboard,
                              @Qualifier("newsController") BaseController newsController) {
        this.newsController = newsController;
        this.keyboard = keyboard;
    }

    @Override
    public void execute() {
        System.out.println(Operations.GET_NEWS_BY_ID.getOperation());
        System.out.println(Constant.NEWS_ID_ENTER);
        try {
            System.out.println(CommandHelper
                    .findCommandHandlerFor("3", newsController)
                    .invoke(newsController, CommandHelper.getKeyboardNumber(Constant.NEWS_ID, keyboard)));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
