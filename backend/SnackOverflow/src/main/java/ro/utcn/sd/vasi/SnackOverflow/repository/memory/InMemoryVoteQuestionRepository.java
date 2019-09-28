package ro.utcn.sd.vasi.SnackOverflow.repository.memory;

import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteQuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryVoteQuestionRepository extends InMemoryGeneralRepository<VoteQuestion> implements VoteQuestionRepository {
    public InMemoryVoteQuestionRepository(InMemoryRepositoryFactory factory) {
        super(factory);
    }

    @Override
    public List<VoteQuestion> findAllVotesWithPostId(int id) {
        return findAll().stream().filter(x -> x.getPostId() == id).collect(Collectors.toList());
    }

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    @Override
    public List<VoteQuestion> findAllVotesFromUserId(int id) {
        return findAll().stream().filter(x -> x.getCineDaId() == id).collect(Collectors.toList());
    }

    /**
     * Finds all the votes to a post of a user with specific id
     *
     * @param userId@return all the votes the user has received
     */
    @Override
    public List<VoteQuestion> findAllVotesTowardsUserId(int userId) {
        return findAll().stream().filter(x -> x.getCinePrimesteId() == userId).collect(Collectors.toList());
    }

    @Override
    public Optional<VoteQuestion> findVoteFromUserForPost(int userId, int postId) {
        return findAll().stream().filter(x -> x.getCineDaId() == userId && x.getPostId() == postId).findAny();
    }
}