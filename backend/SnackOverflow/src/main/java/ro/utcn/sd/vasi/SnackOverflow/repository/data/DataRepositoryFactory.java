package ro.utcn.sd.vasi.SnackOverflow.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "snack_overflow.repository-type", havingValue = "DATA")
@Qualifier("repository")
public class DataRepositoryFactory implements RepositoryFactory {
    private final DataAnswerRepository answerRepository;
    private final DataQuestionRepository questionRepository;
    private final DataUserRepository userRepository;
    private final DataVoteAnswerRepository voteAnswerRepository;
    private final DataVoteQuestionRepository voteQuestionRepository;
    private final DataTagRepository tagRepository;

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
