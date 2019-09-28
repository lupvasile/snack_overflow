package ro.utcn.sd.vasi.SnackOverflow.repository.api;

import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;

import java.util.List;
import java.util.Optional;

public interface VoteQuestionRepository extends GeneralRepository<VoteQuestion> {
    List<VoteQuestion> findAllVotesWithPostId(int id);
    //List<VoteQuestion> findAllVoteQuestionsWithQuestionId(int id);
    //List<VoteQuestion> findAllVoteQuestionsWithAnswerId(int id);

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    List<VoteQuestion> findAllVotesFromUserId(int id);

    /**
     * Finds all the votes to a post of a user with specific id
     *
     * @param id the id of the user
     * @return all the votes the user has received
     */
    List<VoteQuestion> findAllVotesTowardsUserId(int userId);

    Optional<VoteQuestion> findVoteFromUserForPost(int userId, int postId);
}
