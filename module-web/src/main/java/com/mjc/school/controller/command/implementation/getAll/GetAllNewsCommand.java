package com.mjc.school.controller.command.implementation.getAll;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class GetAllNewsCommand implements Command {

    private BaseController newsController;

    @Autowired
    public GetAllNewsCommand(@Qualifier("newsController") BaseController newsController) {
        this.newsController = newsController;
    }
    @Override
    public void execute() {
        System.out.println(Operations.GET_ALL_NEWS.getOperation());
        try {
            if (CommandHelper.findCommandHandlerFor("1", newsController).invoke(newsController) instanceof List<?> allNews) {
                allNews.forEach(System.out::println);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
