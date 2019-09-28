package ro.utcn.sd.vasi.SnackOverflow.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteQuestionRepository;

import java.util.List;
import java.util.Optional;

public interface DataVoteQuestionRepository extends Repository<VoteQuestion, Integer>, VoteQuestionRepository {
    void delete(VoteQuestion element);

    @Override
    default void remove(VoteQuestion element) {
        delete(element);
    }

    List<VoteQuestion> findAllByPostId(Integer postId);

    @Override
    default List<VoteQuestion> findAllVotesWithPostId(int id) {
        return findAllByPostId(id);
    }

    List<VoteQuestion> findAllByCineDaId(Integer id);

    @Override
    default List<VoteQuestion> findAllVotesFromUserId(int id) {
        return findAllByCineDaId(id);
    }

    List<VoteQuestion> findAllByCinePrimesteId(Integer id);

    @Override
    default List<VoteQuestion> findAllVotesTowardsUserId(int userId) {
        return findAllByCinePrimesteId(userId);
    }

    Optional<VoteQuestion> findFirstByCineDaIdAndCinePrimesteId(Integer cineDaId, Integer cinePrimesteId);

    @Override
    default Optional<VoteQuestion> findVoteFromUserForPost(int userId, int postId) {
        return findFirstByCineDaIdAndCinePrimesteId(userId, postId);
    }
}
