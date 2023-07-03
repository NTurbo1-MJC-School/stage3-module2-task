package com.mjc.school.controller.command.implementation.update;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class UpdateAuthorCommand implements Command {

    private BaseController authorController;
    private Scanner keyboard;

    @Autowired
    public UpdateAuthorCommand(@Qualifier("keyboardScanner") Scanner keyboard,
                               @Qualifier("authorController") BaseController authorController) {
        this.authorController = authorController;
        this.keyboard = keyboard;
    }

    @Override
    public void execute() {
        AuthorDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.UPDATE_AUTHOR.getOperation());
                System.out.println(Constant.AUTHOR_ID_ENTER);
                Long authorId = CommandHelper.getKeyboardNumber(Constant.AUTHOR_ID, keyboard);
                System.out.println(Constant.AUTHOR_NAME_ENTER);
                String name = keyboard.nextLine();
                dtoRequest = new AuthorDtoRequest(authorId, name);
                isValid = true;

                System.out.println(CommandHelper
                        .findCommandHandlerFor("8", authorController)
                        .invoke(authorController, dtoRequest));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
