package ro.utcn.sd.vasi.SnackOverflow.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.AnswerRepository;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.QuestionRepository;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.UserRepository;
import ro.utcn.sd.vasi.SnackOverflow.services.AnswerManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.QuestionManagementService;

import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@Profile("!test")
public class ProgramSeed implements CommandLineRunner {
    private final RepositoryFactory factory;
    private final QuestionManagementService questionManagementService;
    private final AnswerManagementService answerManagementService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        addUsers();
        addQuestions();
        addAnswers();
    }

    private void addUsers() {
        UserRepository repository = factory.createUserRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new User(null, "u1", passwordEncoder.encode("pass"), 0, false, false));
            repository.save(new User(null, "u2", passwordEncoder.encode("pass"), 0, true, false));
            repository.save(new User(null, "u3", passwordEncoder.encode("pass"), 0, false, false));
            repository.save(new User(null, "u4", passwordEncoder.encode("pass"), 0, false, true));
        }
    }

    private void addQuestions() {
        QuestionRepository repository = factory.createQuestionRepository();

        User u = factory.createUserRepository().findAll().get(0);
        if (repository.findAll().isEmpty()) {
            questionManagementService.addQuestion(1, "question 1", "ana are mere multe1", new HashSet<>(Arrays.asList(new Tag("tag1"), new Tag("tag2"), new Tag("tag3"))));
            questionManagementService.addQuestion(2, "question 2", "ana are mere multe2", new HashSet<>(Arrays.asList(new Tag("tag2"))));
            questionManagementService.addQuestion(3, "question 3", "ana are mere multe3", new HashSet<>(Arrays.asList(new Tag("tag3"))));
        }
    }

    private void addAnswers() {
        AnswerRepository repository = factory.createAnswerRepository();
        User u = factory.createUserRepository().findAll().get(0);
        Question q = factory.createQuestionRepository().findAll().get(0);

        if (repository.findAll().isEmpty()) {
            answerManagementService.addAnswer(1, q.getId(), "answer 1");
            answerManagementService.addAnswer(2, q.getId(), "answer 2");
            answerManagementService.addAnswer(3, q.getId(), "answer 3");
        }
    }
}
