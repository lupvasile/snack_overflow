package ro.utcn.sd.vasi.SnackOverflow.repository.memory;

import ro.utcn.sd.vasi.SnackOverflow.model.Answer;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.AnswerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAnswerRepository extends InMemoryGeneralRepository<Answer> implements AnswerRepository {
    public InMemoryAnswerRepository(InMemoryRepositoryFactory factory) {
        super(factory);
    }

    @Override
    public List<Answer> findAllbyQuestionId(int questionId) {
        return this.findAll().stream().filter(x -> x.getQuestionId() == questionId).collect(Collectors.toList());
    }

    @Override
    public void remove(Answer element) {
        List<VoteAnswer> votes = factory.createAnswerVoteRepository().findAllVotesWithPostId(element.getId());
        votes.forEach(v -> factory.createAnswerVoteRepository().remove(v));

        super.remove(element);
    }
}
