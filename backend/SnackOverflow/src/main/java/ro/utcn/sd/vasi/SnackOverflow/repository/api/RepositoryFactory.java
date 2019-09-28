package ro.utcn.sd.vasi.SnackOverflow.repository.api;

public interface RepositoryFactory {
    QuestionRepository createQuestionRepository();

    AnswerRepository createAnswerRepository();

    UserRepository createUserRepository();

    VoteAnswerRepository createAnswerVoteRepository();

    VoteQuestionRepository createQuestionVoteRepository();

    TagRepository createTagRepository();
}
