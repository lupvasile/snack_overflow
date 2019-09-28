package ro.utcn.sd.vasi.SnackOverflow.data_assembler;

import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteAnswerRepository;

import java.util.List;
import java.util.Optional;

public class VoteAnswerAssembler extends GeneralAssembler<VoteAnswer, VoteAnswerRepository> implements VoteAnswerRepository {
    public VoteAnswerAssembler(VoteAnswerRepository currRepo, RepositoryFactory repositoryFactory) {
        super(currRepo, repositoryFactory);
    }

    @Override
    protected VoteAnswer makeElementComplete(VoteAnswer element) {
        return element;
    }

    @Override
    public List<VoteAnswer> findAllVotesWithPostId(int id) {
        return currRepo.findAllVotesWithPostId(id);
    }

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    @Override
    public List<VoteAnswer> findAllVotesFromUserId(int id) {
        return currRepo.findAllVotesFromUserId(id);
    }

    /**
     * Finds all the votes to a post of a user with specific id
     *
     * @param userId@return all the votes the user has received
     */
    @Override
    public List<VoteAnswer> findAllVotesTowardsUserId(int userId) {
        return currRepo.findAllVotesTowardsUserId(userId);
    }

    @Override
    public Optional<VoteAnswer> findVoteFromUserForPost(int userId, int postId) {
        return currRepo.findVoteFromUserForPost(userId, postId);
    }
}
