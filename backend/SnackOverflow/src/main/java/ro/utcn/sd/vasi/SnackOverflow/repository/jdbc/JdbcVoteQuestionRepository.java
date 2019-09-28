package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteQuestionRepository;

import java.util.List;
import java.util.Optional;


public class JdbcVoteQuestionRepository extends JdbcGeneralRepository<VoteQuestion> implements VoteQuestionRepository {

    public JdbcVoteQuestionRepository(JdbcTemplate template) {
        super(template, VoteQuestion.class, new VoteQuestionMapper());
    }

    @Override
    public List<VoteQuestion> findAllVotesWithPostId(int id) {
        return template.query("SELECT * FROM " + tableName + " WHERE question = ?", rowMapper, id);
    }

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    @Override
    public List<VoteQuestion> findAllVotesFromUserId(int id) {
        return template.query("SELECT * FROM " + tableName + " WHERE user = ?", rowMapper, id);
    }

    /**
     * Finds all the votes to a post of a user with specific id
     *
     * @param userId@return all the votes the user has received
     */
    @Override
    public List<VoteQuestion> findAllVotesTowardsUserId(int userId) {
        return template.query("SELECT * FROM " + tableName + " WHERE vote_recipient = ?", rowMapper, userId);
    }

    @Override
    public Optional<VoteQuestion> findVoteFromUserForPost(int userId, int postId) {
        List<VoteQuestion> votes = template.query("SELECT * FROM " + tableName + " WHERE user = ? AND question = ?", rowMapper, userId, postId);
        return votes.isEmpty() ? Optional.empty() : Optional.of(votes.get(0));
    }
}
