package com.mjc.school.controller.command.implementation.create;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.service.dto.AuthorDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class CreateAuthorCommand implements Command {

    private BaseController authorController;
    private Scanner keyboard;

    @Autowired
    public CreateAuthorCommand(@Qualifier("keyboardScanner") Scanner keyboard,
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
                System.out.println(Operations.CREATE_AUTHOR.getOperation());
                System.out.println(Constant.AUTHOR_NAME_ENTER);
                String name = keyboard.nextLine();
                dtoRequest = new AuthorDtoRequest(null, name);
                isValid = true;

                System.out.println(CommandHelper.findCommandHandlerFor("6", authorController).invoke(authorController, dtoRequest));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
