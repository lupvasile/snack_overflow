package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.*;


@Component
@ConditionalOnProperty(name = "snack_overflow.repository-type", havingValue = "JDBC")
@Qualifier("repository")
public class JdbcRepositoryFactory implements RepositoryFactory {
    private final JdbcTemplate jdbcTemplate;
    private final JdbcAnswerRepository answerRepository;
    private final JdbcQuestionRepository questionRepository;
    private final JdbcUserRepository userRepository;
    private final JdbcVoteAnswerRepository voteAnswerRepository;
    private final JdbcVoteQuestionRepository voteQuestionRepository;
    private final JdbcTagRepository tagRepository;


    public JdbcRepositoryFactory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        answerRepository = new JdbcAnswerRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
        voteAnswerRepository = new JdbcVoteAnswerRepository(jdbcTemplate);
        voteQuestionRepository = new JdbcVoteQuestionRepository(jdbcTemplate);
        tagRepository = new JdbcTagRepository(jdbcTemplate);
        questionRepository = new JdbcQuestionRepository(jdbcTemplate, tagRepository);

    }

    @Override
    public QuestionRepository createQuestionRepository() {
        return questionRepository;
    }

    @Override
    public AnswerRepository createAnswerRepository() {
        return answerRepository;
    }

    @Override
    public UserRepository createUserRepository() {
        return userRepository;
    }

    @Override
    public VoteAnswerRepository createAnswerVoteRepository() {
        return voteAnswerRepository;
    }

    @Override
    public VoteQuestionRepository createQuestionVoteRepository() {
        return voteQuestionRepository;
    }

    @Override
    public TagRepository createTagRepository() {
        return tagRepository;
    }
}
