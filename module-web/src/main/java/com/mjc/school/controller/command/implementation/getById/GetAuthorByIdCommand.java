package com.mjc.school.controller.command.implementation.getById;

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
public class GetAuthorByIdCommand implements Command {

    private BaseController authorController;
    private Scanner keyboard;

    @Autowired
    public GetAuthorByIdCommand(@Qualifier("authorController") BaseController authorController) {
        this.authorController = authorController;
        this.keyboard = new Scanner(System.in);
    }
    @Override
    public void execute() {
        System.out.println(Operations.GET_AUTHOR_BY_ID.getOperation());
        System.out.println(Constant.AUTHOR_ID_ENTER);
        try {
            Long id = CommandHelper.getKeyboardNumber(Constant.AUTHOR_ID, keyboard);
            System.out.println(CommandHelper
                    .findCommandHandlerFor("4", authorController)
                    .invoke(authorController, id));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
