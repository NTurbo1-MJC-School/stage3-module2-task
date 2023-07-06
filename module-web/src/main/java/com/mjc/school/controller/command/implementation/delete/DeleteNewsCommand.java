package com.mjc.school.controller.command.implementation.delete;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class DeleteNewsCommand implements Command {

    private BaseController newsController;
    private Scanner keyboard;

    @Autowired
    public DeleteNewsCommand(@Qualifier("newsController") BaseController newsController) {
        this.newsController = newsController;
        this.keyboard = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(Operations.REMOVE_NEWS_BY_ID.getOperation());
        System.out.println(Constant.NEWS_ID_ENTER);
        try {
            Long id = CommandHelper.getKeyboardNumber(Constant.NEWS_ID, keyboard);
            System.out.println(CommandHelper.findCommandHandlerFor("9", newsController).invoke(newsController, id));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
