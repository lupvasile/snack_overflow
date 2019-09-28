package ro.utcn.sd.vasi.SnackOverflow.controller.console;

import ro.utcn.sd.vasi.SnackOverflow.services.AnswerManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.QuestionManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

public class AnswerHandler extends CommandHandler {
    public AnswerHandler(ConsoleController consoleController, AnswerManagementService answerManagementService, QuestionManagementService questionManagementService, UserManagementService userManagementService) {
        super(consoleController, answerManagementService, questionManagementService, userManagementService);
    }

    @Override
    boolean handleCommand(String command) {
        switch (command) {
            case "add answer":
                handleAddAnswer();
                return true;
            case "delete answer":
                handleDeleteAnswer();
                return true;
            case "update answer":
                handleUpdateAnswerText();
                return true;
            default:
                return false;
        }
    }

    private void handleAddAnswer() {
        print("Enter question id");
        int questionId = scanner.nextInt();
        scanner.nextLine();

        print("Enter answer text");
        String text = scanner.nextLine().trim();

        answerManagementService.addAnswer(currentUser.getId(), questionId, text);
        print("Answer successfully added");
    }

    private void handleDeleteAnswer() {
        print("Enter answer id");
        int answerId = scanner.nextInt();
        scanner.nextLine();

        answerManagementService.deleteAnswer(currentUser.getId(), answerId);
    }

    private void handleUpdateAnswerText() {
        print("Enter answer id");
        int answerId = scanner.nextInt();
        scanner.nextLine();

        print("Enter new answer text");
        String newText = scanner.nextLine().trim();

        answerManagementService.updateAnswerText(currentUser.getId(), answerId, newText);
    }
}
