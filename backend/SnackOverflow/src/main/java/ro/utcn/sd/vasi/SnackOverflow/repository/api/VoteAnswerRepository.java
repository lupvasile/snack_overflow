package ro.utcn.sd.vasi.SnackOverflow.repository.api;

import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;

import java.util.List;
import java.util.Optional;

public interface VoteAnswerRepository extends GeneralRepository<VoteAnswer> {
    List<VoteAnswer> findAllVotesWithPostId(int id);
    //List<VoteAnswer> findAllVoteAnswersWithQuestionId(int id);
    //List<VoteAnswer> findAllVoteAnswersWithAnswerId(int id);

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    List<VoteAnswer> findAllVotesFromUserId(int id);

    /**
     * Finds all the votes to a post of a user with specific id
     *
     * @param id the id of the user
     * @return all the votes the user has received
     */
    List<VoteAnswer> findAllVotesTowardsUserId(int userId);

    Optional<VoteAnswer> findVoteFromUserForPost(int userId, int postId);
}
