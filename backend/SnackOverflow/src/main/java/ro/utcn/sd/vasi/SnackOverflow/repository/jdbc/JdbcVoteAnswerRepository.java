package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteAnswerRepository;

import java.util.List;
import java.util.Optional;


public class JdbcVoteAnswerRepository extends JdbcGeneralRepository<VoteAnswer> implements VoteAnswerRepository {

    public JdbcVoteAnswerRepository(JdbcTemplate template) {
        super(template, VoteAnswer.class, new VoteAnswerMapper());
    }

    @Override
    public List<VoteAnswer> findAllVotesWithPostId(int id) {
        return template.query("SELECT * FROM " + tableName + " WHERE answer = ?", rowMapper, id);
    }

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    @Override
    public List<VoteAnswer> findAllVotesFromUserId(int id) {
        return template.query("SELECT * FROM " + tableName + " WHERE user = ?", rowMapper, id);
    }

    /**
     * Finds all the votes to a post of a user with specific id
     *
     * @param userId@return all the votes the user has received
     */
    @Override
    public List<VoteAnswer> findAllVotesTowardsUserId(int userId) {
        return template.query("SELECT * FROM " + tableName + " WHERE vote_recipient = ?", rowMapper, userId);
    }

    @Override
    public Optional<VoteAnswer> findVoteFromUserForPost(int userId, int postId) {
        List<VoteAnswer> votes = template.query("SELECT * FROM " + tableName + " WHERE user = ? AND answer = ?", rowMapper, userId, postId);
        return votes.isEmpty() ? Optional.empty() : Optional.of(votes.get(0));
    }
}
