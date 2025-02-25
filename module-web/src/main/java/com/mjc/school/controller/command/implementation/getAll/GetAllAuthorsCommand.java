package com.mjc.school.controller.command.implementation.getAll;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class GetAllAuthorsCommand implements Command {

    private BaseController authorController;

    @Autowired
    public GetAllAuthorsCommand(@Qualifier("authorController") BaseController authorController) {
        this.authorController = authorController;
    }
    @Override
    public void execute() {
        System.out.println(Operations.GET_ALL_AUTHORS.getOperation());
        try {
            if (CommandHelper.findCommandHandlerFor("2", authorController).invoke(authorController) instanceof List<?> allAuthors) {
                allAuthors.forEach(System.out::println);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
