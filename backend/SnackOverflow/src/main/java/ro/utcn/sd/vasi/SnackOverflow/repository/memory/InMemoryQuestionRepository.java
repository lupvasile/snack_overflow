package ro.utcn.sd.vasi.SnackOverflow.repository.memory;

import ro.utcn.sd.vasi.SnackOverflow.model.Answer;
import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.QuestionRepository;

import java.util.List;

public class InMemoryQuestionRepository extends InMemoryGeneralRepository<Question> implements QuestionRepository {

    public InMemoryQuestionRepository(InMemoryRepositoryFactory factory) {
        super(factory);
    }

    @Override
    public void remove(Question element) {
        List<VoteQuestion> votes = factory.createQuestionVoteRepository().findAllVotesWithPostId(element.getId());
        votes.forEach(v -> factory.createQuestionVoteRepository().remove(v));

        List<Answer> answers = factory.createAnswerRepository().findAllbyQuestionId(element.getId());
        answers.forEach(a -> factory.createAnswerRepository().remove(a));

        super.remove(element);
    }
}
