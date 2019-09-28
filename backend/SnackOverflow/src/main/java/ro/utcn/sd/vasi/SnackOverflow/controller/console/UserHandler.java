package ro.utcn.sd.vasi.SnackOverflow.controller.console;

import ro.utcn.sd.vasi.SnackOverflow.services.AnswerManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.QuestionManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

public class UserHandler extends CommandHandler {
    public UserHandler(ConsoleController consoleController, AnswerManagementService answerManagementService, QuestionManagementService questionManagementService, UserManagementService userManagementService) {
        super(consoleController, answerManagementService, questionManagementService, userManagementService);
    }

    @Override
    boolean handleCommand(String command) {
        switch (command) {
            case "ban user":
                handleBanUser();
                return true;
            default:
                return false;
        }
    }

    private void handleBanUser() {
        print("Enter user id");
        int userId = scanner.nextInt();
        scanner.nextLine();

        userManagementService.banUser(currentUser.getId(), userId);
    }
}
