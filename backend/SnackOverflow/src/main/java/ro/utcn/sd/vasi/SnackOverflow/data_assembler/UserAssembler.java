package ro.utcn.sd.vasi.SnackOverflow.data_assembler;

import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.UserRepository;

import java.util.Optional;

public class UserAssembler extends GeneralAssembler<User, UserRepository> implements UserRepository {
    public UserAssembler(UserRepository currRepo, RepositoryFactory repositoryFactory) {
        super(currRepo, repositoryFactory);
    }

    @Override
    protected User makeElementComplete(User element) {
        int voteCount = ScoreCalcuator.computeUserScore(repositoryFactory.createQuestionVoteRepository().findAllVotesTowardsUserId(element.getId()),
                repositoryFactory.createAnswerVoteRepository().findAllVotesTowardsUserId(element.getId()),
                repositoryFactory.createQuestionVoteRepository().findAllVotesFromUserId(element.getId()),
                repositoryFactory.createAnswerVoteRepository().findAllVotesFromUserId(element.getId()));
        element.setScore(voteCount);
        return element;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return currRepo.findByUsername(username);
    }
}
