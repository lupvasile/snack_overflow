package ro.utcn.sd.vasi.SnackOverflow.repository.memory;

import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteAnswerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryVoteAnswerRepository extends InMemoryGeneralRepository<VoteAnswer> implements VoteAnswerRepository {
    public InMemoryVoteAnswerRepository(InMemoryRepositoryFactory factory) {
        super(factory);
    }

    @Override
    public List<VoteAnswer> findAllVotesWithPostId(int id) {
        return findAll().stream().filter(x -> x.getPostId() == id).collect(Collectors.toList());
    }

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    @Override
    public List<VoteAnswer> findAllVotesFromUserId(int id) {
        return findAll().stream().filter(x -> x.getCineDaId() == id).collect(Collectors.toList());
    }

    /**
     * Finds all the votes to a post of a user with specific id
     *
     * @param userId@return all the votes the user has received
     */
    @Override
    public List<VoteAnswer> findAllVotesTowardsUserId(int userId) {
        return findAll().stream().filter(x -> x.getCinePrimesteId() == userId).collect(Collectors.toList());
    }

    @Override
    public Optional<VoteAnswer> findVoteFromUserForPost(int userId, int postId) {
        return findAll().stream().filter(x -> x.getCineDaId() == userId && x.getPostId() == postId).findAny();
    }
}
