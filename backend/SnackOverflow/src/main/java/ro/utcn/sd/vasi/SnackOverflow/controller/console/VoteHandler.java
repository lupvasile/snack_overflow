package ro.utcn.sd.vasi.SnackOverflow.controller.console;

import ro.utcn.sd.vasi.SnackOverflow.services.AnswerManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.QuestionManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

public class VoteHandler extends CommandHandler {

    public VoteHandler(ConsoleController consoleController, AnswerManagementService answerManagementService, QuestionManagementService questionManagementService, UserManagementService userManagementService) {
        super(consoleController, answerManagementService, questionManagementService, userManagementService);
    }

    @Override
    boolean handleCommand(String command) {
        switch (command) {
            case "vote question":
                handleVoteQuestion();
                return true;
            case "vote answer":
                handleVoteAnswer();
                return true;
            default:
                return false;
        }
    }

    private void handleVoteAnswer() {
        print("Enter answer id");
        int id = scanner.nextInt();
        scanner.nextLine();

        print("Enter 1 for upvote or -1 for downvote");
        int vote = scanner.nextInt();
        scanner.nextLine();

        Boolean success;
        Boolean send = vote == 1;
        success = answerManagementService.sendVote(currentUser.getId(), id, send);

        if (success) print("new vote saved");
        else print("vote already existent or tried to vote self");
    }

    private void handleVoteQuestion() {
        print("Enter question id");
        int id = scanner.nextInt();
        scanner.nextLine();

        print("Enter 1 for upvote or -1 for downvote");
        int vote = scanner.nextInt();
        scanner.nextLine();

        Boolean success;
        Boolean send = vote == 1;
        success = questionManagementService.sendVote(currentUser.getId(), id, send);

        if (success) print("new vote saved");
        else print("vote already existent or tried to vote self");
    }
}
