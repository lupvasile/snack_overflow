package ro.utcn.sd.vasi.SnackOverflow.repository.memory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.*;

@Component
@ConditionalOnProperty(name = "snack_overflow.repository-type", havingValue = "MEMORY")
@Qualifier("repository")
public class InMemoryRepositoryFactory implements RepositoryFactory {
    private final InMemoryAnswerRepository answerRepository = new InMemoryAnswerRepository(this);
    private final InMemoryQuestionRepository questionRepository = new InMemoryQuestionRepository(this);
    private final InMemoryUserRepository userRepository = new InMemoryUserRepository(this);
    private final InMemoryVoteAnswerRepository voteAnswerRepository = new InMemoryVoteAnswerRepository(this);
    private final InMemoryVoteQuestionRepository voteQuestionRepository = new InMemoryVoteQuestionRepository(this);
    private final InMemoryTagRepository tagRepository = new InMemoryTagRepository(this);

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
