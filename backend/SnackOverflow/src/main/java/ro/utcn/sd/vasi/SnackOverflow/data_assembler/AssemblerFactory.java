package ro.utcn.sd.vasi.SnackOverflow.data_assembler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.*;

@Component
@Primary
@ConditionalOnProperty(name = "snack_overflow.use-assembler", havingValue = "TRUE")
public class AssemblerFactory implements RepositoryFactory {
    private final RepositoryFactory repositoryFactory;

    private final AnswerAssembler answerAssembler;
    private final QuestionAssembler questionAssembler;
    private final UserAssembler userAssembler;
    private final VoteAnswerAssembler voteAnswerAssembler;
    private final VoteQuestionAssembler voteQuestionAssembler;
    private final TagAssembler tagAssembler;

    public AssemblerFactory(@Qualifier("repository") RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;

        answerAssembler = new AnswerAssembler(repositoryFactory.createAnswerRepository(), repositoryFactory);
        questionAssembler = new QuestionAssembler(repositoryFactory.createQuestionRepository(), repositoryFactory);
        userAssembler = new UserAssembler(repositoryFactory.createUserRepository(), repositoryFactory);
        voteAnswerAssembler = new VoteAnswerAssembler(repositoryFactory.createAnswerVoteRepository(), repositoryFactory);
        voteQuestionAssembler = new VoteQuestionAssembler(repositoryFactory.createQuestionVoteRepository(), repositoryFactory);
        tagAssembler = new TagAssembler(repositoryFactory.createTagRepository(), repositoryFactory);
    }

    @Override
    public TagRepository createTagRepository() {
        return tagAssembler;
    }

    @Override
    public QuestionRepository createQuestionRepository() {
        return questionAssembler;
    }

    @Override
    public AnswerRepository createAnswerRepository() {
        return answerAssembler;
    }

    @Override
    public UserRepository createUserRepository() {
        return userAssembler;
    }

    @Override
    public VoteAnswerAssembler createAnswerVoteRepository() {
        return voteAnswerAssembler;
    }

    @Override
    public VoteQuestionAssembler createQuestionVoteRepository() {
        return voteQuestionAssembler;
    }
}
