package ro.utcn.sd.vasi.SnackOverflow.controller.console;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.services.AnswerManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.QuestionManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Data
@RequiredArgsConstructor
@Profile("!test")
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final AnswerManagementService answerManagementService;
    private final QuestionManagementService questionManagementService;
    private final UserManagementService userManagementService;
    private final List<CommandHandler> commandHandlers = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;
    private User currentUser;

    private void addHandlers() {
        commandHandlers.add(new AnswerHandler(this, answerManagementService, questionManagementService, userManagementService));
        commandHandlers.add(new QuestionHandler(this, answerManagementService, questionManagementService, userManagementService));
        commandHandlers.add(new UserHandler(this, answerManagementService, questionManagementService, userManagementService));
        commandHandlers.add(new VoteHandler(this, answerManagementService, questionManagementService, userManagementService));
    }

    @Override
    public void run(String... args) {
        print("Welcome to the snack overflow");

        doLogin();

        if (currentUser.getIsBlocked()) {
            print("YOU ARE BANNED");
            print("YOU CAN'T PERFORM ANY ACTIONS");
            return;
        }

        addHandlers();

        while (true) {
            try {
                print("Enter a command: ");
                String command = scanner.nextLine().trim();
                if (command.equals("exit")) break;

                boolean wasExecuted = false;
                for (CommandHandler c : commandHandlers)
                    wasExecuted = wasExecuted || c.handleCommand(command);

                if (!wasExecuted) print("Unknown command. Try again.");
            } catch (Exception e) {
                print(e.getClass().getSimpleName());
            }

        }
    }

    private void doLogin() {
        //currentUser = userManagementService.getLogin("u2","pass").orElse(null);

        boolean done = false;
        while (!done) {
            print("username: ");
            String username = scanner.next().trim();

            print("passoword: ");
            String password = scanner.next().trim();
            scanner.nextLine();

            currentUser = userManagementService.getLogin(username, password, passwordEncoder).orElse(null);
            if (currentUser == null) {
                print("wrong login data");
            } else {
                print("Welcome " + currentUser.getUsername());
                done = true;
            }
        }
    }

    void print(String value) {
        System.out.println(value);
    }
}
