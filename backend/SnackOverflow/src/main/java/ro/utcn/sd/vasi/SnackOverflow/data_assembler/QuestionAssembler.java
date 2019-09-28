package ro.utcn.sd.vasi.SnackOverflow.data_assembler;

import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.QuestionRepository;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;

public class QuestionAssembler extends GeneralAssembler<Question, QuestionRepository> implements QuestionRepository {

    public QuestionAssembler(QuestionRepository currRepo, RepositoryFactory repositoryFactory) {
        super(currRepo, repositoryFactory);
    }

    @Override
    protected Question makeElementComplete(Question element) {
        int voteCount = ScoreCalcuator.computeQuestionScore(repositoryFactory.createQuestionVoteRepository().findAllVotesWithPostId(element.getId()));
        element.setVoteCount(voteCount);
        return element;
    }
}
