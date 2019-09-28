package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.*;

import javax.persistence.EntityManager;

@Component
@ConditionalOnProperty(name = "snack_overflow.repository-type", havingValue = "JPA")
@Qualifier("repository")
public class HibernateRepositoryFactory implements RepositoryFactory {
    private final EntityManager entityManager;
    private final HibernateAnswerRepository answerRepository;
    private final HibernateQuestionRepository questionRepository;
    private final HibernateUserRepository userRepository;
    private final HibernateVoteAnswerRepository voteAnswerRepository;
    private final HibernateVoteQuestionRepository voteQuestionRepository;
    private final HibernateTagRepository tagRepository;


    public HibernateRepositoryFactory(EntityManager entityManager) {
        this.entityManager = entityManager;

        answerRepository = new HibernateAnswerRepository(entityManager);
        questionRepository = new HibernateQuestionRepository(entityManager);
        userRepository = new HibernateUserRepository(entityManager);
        voteAnswerRepository = new HibernateVoteAnswerRepository(entityManager);
        voteQuestionRepository = new HibernateVoteQuestionRepository(entityManager);
        tagRepository = new HibernateTagRepository(entityManager);

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
