package com.mjc.school.controller.command;

import com.mjc.school.controller.command.implementation.create.CreateAuthorCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandReceiver {
    private Command createAuthorCommand;
    private Command createNewsCommand;
    private Command deleteAuthorCommand;
    private Command deleteNewsCommand;
    private Command getAllAuthorsCommand;
    private Command getAllNewsCommand;
    private Command getAuthorByIdCommand;
    private Command getNewsByIdCommand;
    private Command updateAuthorCommand;
    private Command updateNewsCommand;
    private Command exitCommand;
    private Command printMainMenuCommand;

    @Autowired
    public CommandReceiver(@Qualifier("createAuthorCommand") Command createAuthorCommand,
                           @Qualifier("createNewsCommand") Command createNewsCommand,
                           @Qualifier("deleteAuthorCommand") Command deleteAuthorCommand,
                           @Qualifier("deleteNewsCommand") Command deleteNewsCommand,
                           @Qualifier("getAllAuthorsCommand") Command getAllAuthorsCommand,
                           @Qualifier("getAllNewsCommand") Command getAllNewsCommand,
                           @Qualifier("getAuthorByIdCommand") Command getAuthorByIdCommand,
                           @Qualifier("getNewsByIdCommand") Command getNewsByIdCommand,
                           @Qualifier("updateAuthorCommand") Command updateAuthorCommand,
                           @Qualifier("updateNewsCommand") Command updateNewsCommand,
                           @Qualifier("exitCommand") Command exitCommand,
                           @Qualifier("printMainMenuCommand") Command printMainMenuCommand) {
        this.createAuthorCommand = createAuthorCommand;
        this.createNewsCommand = createNewsCommand;
        this.deleteAuthorCommand = deleteAuthorCommand;
        this.deleteNewsCommand = deleteNewsCommand;
        this.getAllAuthorsCommand = getAllAuthorsCommand;
        this.getAllNewsCommand = getAllNewsCommand;
        this.getAuthorByIdCommand = getAuthorByIdCommand;
        this.getNewsByIdCommand = getNewsByIdCommand;
        this.updateAuthorCommand = updateAuthorCommand;
        this.updateNewsCommand = updateNewsCommand;
        this.exitCommand = exitCommand;
        this.printMainMenuCommand = printMainMenuCommand;
    }

    public void createAuthor() {
        createAuthorCommand.execute();
    }

    public void createNews() {
        createNewsCommand.execute();
    }

    public void deleteAuthor() {
        deleteAuthorCommand.execute();
    }

    public void deleteNews() {
        deleteNewsCommand.execute();
    }

    public void getAllAuthors() {
        getAllAuthorsCommand.execute();
    }

    public void getAllNews() {
        getAllNewsCommand.execute();
    }

    public void getAuthorById() {
        getAuthorByIdCommand.execute();
    }

    public void getNewsById() {
        getNewsByIdCommand.execute();
    }

    public void updateAuthor() {
        updateAuthorCommand.execute();
    }

    public void updateNews() {
        updateNewsCommand.execute();
    }

    public void exit() {
        exitCommand.execute();
    }

    public void printMainMenu() {
        printMainMenuCommand.execute();
    }
    
}
