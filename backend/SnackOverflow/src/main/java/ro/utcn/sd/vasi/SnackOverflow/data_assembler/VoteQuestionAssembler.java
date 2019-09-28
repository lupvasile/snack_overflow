package ro.utcn.sd.vasi.SnackOverflow.data_assembler;

import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteQuestionRepository;

import java.util.List;
import java.util.Optional;

public class VoteQuestionAssembler extends GeneralAssembler<VoteQuestion, VoteQuestionRepository> implements VoteQuestionRepository {
    public VoteQuestionAssembler(VoteQuestionRepository currRepo, RepositoryFactory repositoryFactory) {
        super(currRepo, repositoryFactory);
    }

    @Override
    protected VoteQuestion makeElementComplete(VoteQuestion element) {
        return element;
    }

    @Override
    public List<VoteQuestion> findAllVotesWithPostId(int id) {
        return currRepo.findAllVotesWithPostId(id);
    }

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    @Override
    public List<VoteQuestion> findAllVotesFromUserId(int id) {
        return currRepo.findAllVotesFromUserId(id);
    }

    /**
     * Finds all the votes to a post of a user with specific id
     *
     * @param userId@return all the votes the user has received
     */
    @Override
    public List<VoteQuestion> findAllVotesTowardsUserId(int userId) {
        return currRepo.findAllVotesTowardsUserId(userId);
    }

    @Override
    public Optional<VoteQuestion> findVoteFromUserForPost(int userId, int postId) {
        return currRepo.findVoteFromUserForPost(userId, postId);
    }
}
