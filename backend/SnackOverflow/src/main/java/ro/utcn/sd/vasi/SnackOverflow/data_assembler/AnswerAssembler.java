package ro.utcn.sd.vasi.SnackOverflow.data_assembler;

import ro.utcn.sd.vasi.SnackOverflow.model.Answer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.AnswerRepository;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;

import java.util.List;

public class AnswerAssembler extends GeneralAssembler<Answer, AnswerRepository> implements AnswerRepository {

    public AnswerAssembler(AnswerRepository currRepo, RepositoryFactory repositoryFactory) {
        super(currRepo, repositoryFactory);
    }

    @Override
    public List<Answer> findAllbyQuestionId(int questionId) {
        List<Answer> res = currRepo.findAllbyQuestionId(questionId);
        res.forEach(x -> makeElementComplete(x));
        return res;
    }

    @Override
    protected Answer makeElementComplete(Answer element) {
        int voteCount = ScoreCalcuator.computeAnswerScore(repositoryFactory.createAnswerVoteRepository().findAllVotesWithPostId(element.getId()));
        element.setVoteCount(voteCount);
        return element;
    }
}
