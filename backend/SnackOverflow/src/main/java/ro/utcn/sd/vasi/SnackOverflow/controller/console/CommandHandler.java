package ro.utcn.sd.vasi.SnackOverflow.controller.console;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.services.AnswerManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.QuestionManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

import java.util.Scanner;

@Component
public abstract class CommandHandler {
    protected final ConsoleController consoleController;
    protected final AnswerManagementService answerManagementService;
    protected final QuestionManagementService questionManagementService;
    protected final UserManagementService userManagementService;
    protected Scanner scanner;
    protected User currentUser;

    public CommandHandler(ConsoleController consoleController, AnswerManagementService answerManagementService, QuestionManagementService questionManagementService, UserManagementService userManagementService) {
        this.consoleController = consoleController;
        this.answerManagementService = answerManagementService;
        this.questionManagementService = questionManagementService;
        this.userManagementService = userManagementService;

        scanner = consoleController.getScanner();
        currentUser = consoleController.getCurrentUser();
    }

    abstract boolean handleCommand(String command);

    void print(String text) {
        consoleController.print(text);
    }
}
